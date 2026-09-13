package com.beautymart.controller;

import com.beautymart.entity.*;
import com.beautymart.repository.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin
public class ReviewController {
    private final ReviewRepository reviews;
    private final ProductRepository products;
    private final UserRepository users;
    private final OrderItemRepository orderItems;

    public ReviewController(ReviewRepository reviews, ProductRepository products,
                             UserRepository users, OrderItemRepository orderItems){
        this.reviews=reviews; this.products=products; this.users=users; this.orderItems=orderItems;
    }

    // GET all reviews for a product + average rating
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> forProduct(@PathVariable Long productId){
        Product p = products.findById(productId).orElseThrow();
        List<Review> list = reviews.findByProductOrderByCreatedAtDesc(p);
        double avg = list.stream().mapToInt(Review::getRating).average().orElse(0.0);
        List<Map<String,Object>> data = list.stream().map(r -> {
            Map<String,Object> m = new HashMap<>();
            m.put("id", r.getId());
            m.put("userName", r.getUser().getName());
            m.put("rating", r.getRating());
            m.put("comment", r.getComment());
            m.put("createdAt", r.getCreatedAt());
            return m;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("average", Math.round(avg*10.0)/10.0, "count", list.size(), "reviews", data));
    }

    // POST a new review — only allowed if the user has actually ordered this product
    @PostMapping("/{productId}/{userId}")
    public ResponseEntity<?> add(@PathVariable Long productId, @PathVariable Long userId,
                                  @RequestBody Map<String,Object> body){
        Product p = products.findById(productId).orElseThrow();
        User u = users.findById(userId).orElseThrow();

        if(!orderItems.existsByUserAndProduct(u, p)){
            return ResponseEntity.badRequest().body(Map.of("message","You can only review products you have purchased."));
        }
        if(reviews.existsByProductAndUser(p, u)){
            return ResponseEntity.badRequest().body(Map.of("message","You have already reviewed this product."));
        }

        Object ratingObj = body.get("rating");
        int rating = ratingObj instanceof Number ? ((Number) ratingObj).intValue() : 0;
        if(rating < 1 || rating > 5){
            return ResponseEntity.badRequest().body(Map.of("message","Rating must be between 1 and 5."));
        }
        String comment = String.valueOf(body.getOrDefault("comment",""));

        Review r = new Review(p, u, rating, comment);
        reviews.save(r);
        return ResponseEntity.ok(Map.of("message","Review added successfully."));
    }
}