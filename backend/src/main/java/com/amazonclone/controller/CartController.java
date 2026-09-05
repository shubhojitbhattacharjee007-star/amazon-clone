package com.amazonclone.controller;

import com.amazonclone.dto.AddCartItemRequest;
import com.amazonclone.dto.CartResponse;
import com.amazonclone.dto.UpdateCartItemRequest;
import com.amazonclone.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponse> getCart() {
        return ResponseEntity.ok(cartService.getCart());
    }

    @PostMapping("/items")
    public ResponseEntity<CartResponse> addItem(
            @Valid @RequestBody AddCartItemRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cartService.addItem(request));
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> updateItemQuantity(
            @PathVariable UUID itemId,
            @Valid @RequestBody UpdateCartItemRequest request
    ) {
        return ResponseEntity.ok(cartService.updateItemQuantity(itemId, request));
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> removeItem(@PathVariable UUID itemId) {
        return ResponseEntity.ok(cartService.removeItem(itemId));
    }

    @DeleteMapping
    public ResponseEntity<CartResponse> clearCart() {
        return ResponseEntity.ok(cartService.clearCart());
    }
}
