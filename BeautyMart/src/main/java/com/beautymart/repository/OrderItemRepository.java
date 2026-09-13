package com.beautymart.repository;

import com.beautymart.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    @org.springframework.data.jpa.repository.Query(
        "select count(oi) > 0 from OrderItem oi where oi.order.user = :user and oi.product = :product")
    boolean existsByUserAndProduct(@org.springframework.data.repository.query.Param("user") User user,
                                    @org.springframework.data.repository.query.Param("product") Product product);

    List<OrderItem> findByProduct_Seller(User seller);
}