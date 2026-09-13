package com.beautymart.repository;

import com.beautymart.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
 List<Order> findByUserOrderByCreatedAtDesc(User user);
}
