package com.amazonclone.controller;

import com.amazonclone.dto.AdminProductRequest;
import com.amazonclone.entity.Product;
import com.amazonclone.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @Valid @RequestBody AdminProductRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createProduct(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable UUID id,
            @Valid @RequestBody AdminProductRequest request
    ) {
        return ResponseEntity.ok(
                productService.updateProduct(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
