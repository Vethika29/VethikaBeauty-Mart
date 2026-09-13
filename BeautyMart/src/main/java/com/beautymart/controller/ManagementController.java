package com.beautymart.controller;
import com.beautymart.entity.*;
import com.beautymart.repository.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/manage")
@CrossOrigin
public class ManagementController {
 private final ProductRepository products; private final UserRepository users; private final OrderRepository orders;
 public ManagementController(ProductRepository products,UserRepository users,OrderRepository orders){
 this.products=products;this.users=users;this.orders=orders;
 }
 @GetMapping("/stats")
 public Map<String,Object> stats(){
 return Map.of("products",products.count(),"buyers",users.countByRole("BUYER"),
 "sellers",users.countByRole("SELLER"),"orders",orders.count());
 }
 @GetMapping("/users")
 public List<User> allUsers(){
 return users.findAll();
 }
 @GetMapping("/orders")
 public List<Order> allOrders(){
 return orders.findAll();
 }
 @PostMapping("/products")
 public Product add(@RequestBody Product p){p.setId(null); return products.save(p);}
 @PutMapping("/products/{id}")
 public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Product incoming){
 return products.findById(id).map(p->{
 p.setName(incoming.getName());p.setBrand(incoming.getBrand());p.setCategory(incoming.getCategory());
 p.setPrice(incoming.getPrice());p.setMrp(incoming.getMrp());p.setColor(incoming.getColor());
 p.setDescription(incoming.getDescription());p.setImage(incoming.getImage());p.setStock(incoming.getStock());
 p.setRating(incoming.getRating()); return ResponseEntity.ok(products.save(p));
 }).orElseGet(()->ResponseEntity.notFound().build());
 }
 @DeleteMapping("/products/{id}")
 public ResponseEntity<?> delete(@PathVariable Long id){products.deleteById(id);return ResponseEntity.ok(Map.of("message","deleted"));}
}