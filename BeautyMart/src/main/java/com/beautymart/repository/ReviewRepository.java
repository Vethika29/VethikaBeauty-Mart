package com.beautymart.repository;

import com.beautymart.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByProductOrderByCreatedAtDesc(Product product);
    boolean existsByProductAndUser(Product product, User user);
}