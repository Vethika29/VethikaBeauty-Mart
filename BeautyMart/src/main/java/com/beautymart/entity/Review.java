 package com.beautymart.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="reviews")
public class Review {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false) private Product product;
    @ManyToOne(optional=false) private User user;

    @Column(nullable=false) private Integer rating; // 1 to 5
    @Column(length=1000) private String comment;
    @Column(nullable=false) private LocalDateTime createdAt;

    public Review(){}
    public Review(Product product, User user, Integer rating, String comment){
        this.product=product; this.user=user; this.rating=rating; this.comment=comment;
        this.createdAt=LocalDateTime.now();
    }

    public Long getId(){return id;}
    public Product getProduct(){return product;}
    public User getUser(){return user;}
    public Integer getRating(){return rating;}
    public void setRating(Integer v){rating=v;}
    public String getComment(){return comment;}
    public void setComment(String v){comment=v;}
    public LocalDateTime getCreatedAt(){return createdAt;}
}
