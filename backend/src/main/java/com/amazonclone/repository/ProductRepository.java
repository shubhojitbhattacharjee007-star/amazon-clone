package com.amazonclone.repository;

import com.amazonclone.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByCategoryAndActiveTrue(String category);

    List<Product> findByActiveTrue();

    List<Product> findByNameContainingIgnoreCaseAndActiveTrue(String name);
}
