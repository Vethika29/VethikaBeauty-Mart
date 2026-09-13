package com.beautymart.controller;

import com.beautymart.entity.*;
import com.beautymart.repository.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {
 private final UserRepository users; private final CartItemRepository cart; private final OrderRepository orders;

 public OrderController(UserRepository users,CartItemRepository cart,OrderRepository orders){
 this.users=users;this.cart=cart;this.orders=orders;
 }

 @GetMapping("/user/{userId}")
 public List<Order> userOrders(@PathVariable Long userId){
 return orders.findByUserOrderByCreatedAtDesc(users.findById(userId).orElseThrow());
 }

 @PostMapping("/checkout/{userId}")
 public ResponseEntity<?> checkout(@PathVariable Long userId){
 User u=users.findById(userId).orElseThrow();
 List<CartItem> items=cart.findByUser(u);
 if(items.isEmpty()) return ResponseEntity.badRequest().body(Map.of("message","Cart is empty."));
 BigDecimal total=BigDecimal.ZERO;
 for(CartItem i:items) total=total.add(i.getProduct().getPrice().multiply(BigDecimal.valueOf(i.getQuantity())));
 Order o=new Order(u,total,"PLACED");
 for(CartItem i:items){
 Product p=i.getProduct();
 if(p.getStock()<i.getQuantity()) return ResponseEntity.badRequest().body(Map.of("message","Insufficient stock for "+p.getName()));
 p.setStock(p.getStock()-i.getQuantity());
 OrderItem oi=new OrderItem(o,p,i.getQuantity(),p.getPrice());
 oi.getOrder().getItems().add(oi);
 }
 orders.save(o);
 cart.deleteAll(items);
 return ResponseEntity.ok(o);
 }
}
