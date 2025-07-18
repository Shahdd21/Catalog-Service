package com.example.ProductService.repository;

import com.example.ProductService.model.entity.Category;
import com.example.ProductService.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
    Optional<Product> findByNameAndCategory(String name, Category categoryId);

}
