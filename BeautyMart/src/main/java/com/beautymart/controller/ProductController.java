package com.beautymart.controller;

import com.beautymart.entity.Product;
import com.beautymart.repository.ProductRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {
 private final ProductRepository products;
 public ProductController(ProductRepository products){this.products=products;}

 @GetMapping
 public List<Product> all(@RequestParam(required=false) String q, @RequestParam(required=false) String category){
 if(q!=null && !q.isBlank()) return products.findByNameContainingIgnoreCaseOrBrandContainingIgnoreCase(q,q);
 if(category!=null && !category.isBlank() && !"All".equalsIgnoreCase(category)) return products.findByCategoryIgnoreCase(category);
 return products.findAll();
 }

 @GetMapping("/{id}")
 public ResponseEntity<?> one(@PathVariable Long id){
 return products.findById(id).<ResponseEntity<?>>map(ResponseEntity::ok)
 .orElseGet(()->ResponseEntity.notFound().build());
 }
}
