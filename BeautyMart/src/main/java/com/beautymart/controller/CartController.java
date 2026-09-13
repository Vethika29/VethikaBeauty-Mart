package com.beautymart.controller;

import com.beautymart.entity.*;
import com.beautymart.repository.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {
 private final UserRepository users; private final ProductRepository products; private final CartItemRepository cart;

 public CartController(UserRepository users,ProductRepository products,CartItemRepository cart){
 this.users=users;this.products=products;this.cart=cart;
 }
 private User user(Long id){return users.findById(id).orElseThrow();}

 @GetMapping("/{userId}")
 public List<CartItem> get(@PathVariable Long userId){return cart.findByUser(user(userId));}

 @PostMapping("/{userId}/{productId}")
 public List<CartItem> add(@PathVariable Long userId,@PathVariable Long productId){
 User u=user(userId); Product p=products.findById(productId).orElseThrow();
 CartItem item=cart.findByUserAndProduct(u,p).orElse(new CartItem(u,p,0));
 item.setQuantity(item.getQuantity()+1); cart.save(item);
 return cart.findByUser(u);
 }

 @PutMapping("/{userId}/{itemId}")
 public List<CartItem> update(@PathVariable Long userId,@PathVariable Long itemId,@RequestBody Map<String,Integer> body){
 CartItem i=cart.findById(itemId).orElseThrow();
 int q=body.getOrDefault("quantity",1);
 if(q<=0) cart.delete(i); else i.setQuantity(q); if(q>0) cart.save(i);
 return cart.findByUser(user(userId));
 }

 @DeleteMapping("/{userId}/{itemId}")
 public List<CartItem> remove(@PathVariable Long userId,@PathVariable Long itemId){
 cart.deleteById(itemId); return cart.findByUser(user(userId));
 }
}
