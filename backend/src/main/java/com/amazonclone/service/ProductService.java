package com.amazonclone.service;

import com.amazonclone.dto.AdminProductRequest;
import com.amazonclone.entity.Product;
import com.amazonclone.exception.BaseException;
import com.amazonclone.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findByActiveTrue();
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .filter(Product::isActive)
                .orElseThrow(() -> new BaseException(
                        "Product not found",
                        HttpStatus.NOT_FOUND,
                        "PRODUCT_NOT_FOUND"
                ));
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryAndActiveTrue(category);
    }

    public List<Product> searchProducts(String name) {
        return productRepository.findByNameContainingIgnoreCaseAndActiveTrue(name);
    }

    public Product createProduct(AdminProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .category(request.getCategory())
                .brand(request.getBrand())
                .imageUrl(request.getImageUrl())
                .active(true)
                .build();

        return productRepository.save(product);
    }

    public Product updateProduct(UUID id, AdminProductRequest request) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        "Product not found",
                        HttpStatus.NOT_FOUND,
                        "PRODUCT_NOT_FOUND"
                ));

        existingProduct.setName(request.getName());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setStockQuantity(request.getStockQuantity());
        existingProduct.setCategory(request.getCategory());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setImageUrl(request.getImageUrl());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        "Product not found",
                        HttpStatus.NOT_FOUND,
                        "PRODUCT_NOT_FOUND"
                ));

        product.setActive(false);
        productRepository.save(product);
    }
}
