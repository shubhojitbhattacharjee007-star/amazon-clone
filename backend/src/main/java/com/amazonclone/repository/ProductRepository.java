package com.amazonclone.repository;

import com.amazonclone.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByCategory(String category);

    List<Product> findByActiveTrue();

    List<Product> findByNameContainingIgnoreCase(String name);
}
