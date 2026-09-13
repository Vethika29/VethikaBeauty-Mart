package com.beautymart.controller;

import com.beautymart.entity.User;
import com.beautymart.repository.UserRepository;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
 private final UserRepository users;
 private final BCryptPasswordEncoder encoder;

 public AuthController(UserRepository users, BCryptPasswordEncoder encoder){this.users=users;this.encoder=encoder;}

 @PostMapping("/login")
 public ResponseEntity<?> login(@RequestBody Map<String,String> body){
 String email=body.getOrDefault("email","").trim();
 String password=body.getOrDefault("password","");
 return users.findByEmail(email)
 .filter(u -> encoder.matches(password,u.getPassword()))
 .<ResponseEntity<?>>map(u -> ResponseEntity.ok(Map.of(
 "id",u.getId(),"name",u.getName(),"email",u.getEmail(),"role",u.getRole())))
 .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
 .body(Map.of("message","Invalid email or password")));
 }

 @PostMapping("/register")
 public ResponseEntity<?> register(@RequestBody Map<String,String> body){
 String name=body.getOrDefault("name","").trim();
 String email=body.getOrDefault("email","").trim();
 String password=body.getOrDefault("password","");
 if(name.isBlank()||email.isBlank()||password.length()<6)
 return ResponseEntity.badRequest().body(Map.of("message","Enter valid details. Password must be at least 6 characters."));
 if(users.findByEmail(email).isPresent())
 return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message","Email already registered."));
 User u=users.save(new User(name,email,encoder.encode(password),"BUYER"));
 return ResponseEntity.ok(Map.of("id",u.getId(),"name",u.getName(),"email",u.getEmail(),"role",u.getRole()));
 }
}
