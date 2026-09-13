package com.beautymart.entity;

import jakarta.persistence.*;

@Entity
@Table(name="cart_items")
public class CartItem {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 @ManyToOne(optional=false) private User user;
 @ManyToOne(optional=false) private Product product;
 @Column(nullable=false) private Integer quantity;

 public CartItem(){}
 public CartItem(User user, Product product, Integer quantity){
 this.user=user;this.product=product;this.quantity=quantity;
 }
 public Long getId(){return id;}
 public User getUser(){return user;}
 public Product getProduct(){return product;}
 public Integer getQuantity(){return quantity;}
 public void setQuantity(Integer v){quantity=v;}
}
