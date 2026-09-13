package com.beautymart.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name="products")
public class Product {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 @Column(nullable=false) private String name;
 private String brand;
 private String category;
 @Column(nullable=false, precision=10, scale=2) private BigDecimal price;
 private BigDecimal mrp;
 private String color;
 @Column(length=1000) private String description;
 private String image;
 private Integer stock;
 private Double rating;
 @ManyToOne private User seller;
 public Product(){}
 public Product(String name,String brand,String category,BigDecimal price,BigDecimal mrp,
 String color,String description,String image,Integer stock,Double rating){
 this.name=name;this.brand=brand;this.category=category;this.price=price;this.mrp=mrp;
 this.color=color;this.description=description;this.image=image;this.stock=stock;this.rating=rating;
 }
 public Long getId(){return id;}
 public void setId(Long v){id=v;}
 public String getName(){return name;}
 public void setName(String v){name=v;}
 public String getBrand(){return brand;}
 public void setBrand(String v){brand=v;}
 public String getCategory(){return category;}
 public void setCategory(String v){category=v;}
 public BigDecimal getPrice(){return price;}
 public void setPrice(BigDecimal v){price=v;}
 public BigDecimal getMrp(){return mrp;}
 public void setMrp(BigDecimal v){mrp=v;}
 public String getColor(){return color;}
 public void setColor(String v){color=v;}
 public String getDescription(){return description;}
 public void setDescription(String v){description=v;}
 public String getImage(){return image;}
 public void setImage(String v){image=v;}
 public Integer getStock(){return stock;}
 public void setStock(Integer v){stock=v;}
 public Double getRating(){return rating;}
 public void setRating(Double v){rating=v;}
 public User getSeller(){return seller;}
 public void setSeller(User v){seller=v;}
}