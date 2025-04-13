package com.example.demo.database.repository;

import com.example.demo.database.entity.Product;
import com.example.demo.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByName(String name);

    List<Product> findAll();
}
