package com.beautymart.controller;

import com.beautymart.entity.*;
import com.beautymart.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/seller")
@CrossOrigin
public class SellerController {
    private final UserRepository users;
    private final ProductRepository products;
    private final OrderItemRepository orderItems;

    public SellerController(UserRepository users, ProductRepository products, OrderItemRepository orderItems){
        this.users=users; this.products=products; this.orderItems=orderItems;
    }

    // Products belonging to this seller
    @GetMapping("/{sellerId}/products")
    public List<Product> myProducts(@PathVariable Long sellerId){
        User seller = users.findById(sellerId).orElseThrow();
        return products.findBySeller(seller);
    }

    // Orders that include this seller's products
    @GetMapping("/{sellerId}/orders")
    public List<Map<String,Object>> myOrders(@PathVariable Long sellerId){
        User seller = users.findById(sellerId).orElseThrow();
        List<OrderItem> items = orderItems.findByProduct_Seller(seller);
        return items.stream().map(oi -> {
            Map<String,Object> m = new HashMap<>();
            m.put("orderId", oi.getOrder().getId());
            m.put("orderDate", oi.getOrder().getCreatedAt());
            m.put("buyerName", oi.getOrder().getUser().getName());
            m.put("productName", oi.getProduct().getName());
            m.put("quantity", oi.getQuantity());
            m.put("price", oi.getPrice());
            m.put("status", oi.getOrder().getStatus());
            return m;
        }).collect(Collectors.toList());
    }
}