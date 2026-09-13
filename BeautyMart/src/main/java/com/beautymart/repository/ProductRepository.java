package com.beautymart.repository;
import com.beautymart.entity.Product;
import com.beautymart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ProductRepository extends JpaRepository<Product,Long> {
 List<Product> findByNameContainingIgnoreCaseOrBrandContainingIgnoreCase(String name,String brand);
 List<Product> findByCategoryIgnoreCase(String category);
 List<Product> findBySeller(User seller);
}