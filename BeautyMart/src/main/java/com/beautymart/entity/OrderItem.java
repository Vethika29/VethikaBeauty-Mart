package com.beautymart.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name="order_items")
public class OrderItem {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 @ManyToOne(optional=false) @JsonIgnore private Order order;
 @ManyToOne(optional=false) private Product product;
 @Column(nullable=false) private Integer quantity;
 @Column(nullable=false, precision=10, scale=2) private BigDecimal price;
 public OrderItem(){}
 public OrderItem(Order order, Product product, Integer quantity, BigDecimal price){
 this.order=order;this.product=product;this.quantity=quantity;this.price=price;
 }
 public Long getId(){return id;}
 public Order getOrder(){return order;}
 public Product getProduct(){return product;}
 public Integer getQuantity(){return quantity;}
 public BigDecimal getPrice(){return price;}
}
