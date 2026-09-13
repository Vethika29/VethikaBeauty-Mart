package com.beautymart.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 @ManyToOne(optional=false) private User user;
 @Column(nullable=false, precision=10, scale=2) private BigDecimal totalAmount;
 @Column(nullable=false) private String status;
 @Column(nullable=false) private LocalDateTime createdAt;
 @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true)
 private List<OrderItem> items = new ArrayList<>();

 public Order(){}
 public Order(User user, BigDecimal totalAmount, String status){
 this.user=user;this.totalAmount=totalAmount;this.status=status;this.createdAt=LocalDateTime.now();
 }
 public Long getId(){return id;}
 public User getUser(){return user;}
 public BigDecimal getTotalAmount(){return totalAmount;}
 public String getStatus(){return status;}
 public void setStatus(String v){status=v;}
 public LocalDateTime getCreatedAt(){return createdAt;}
 public List<OrderItem> getItems(){return items;}
}
