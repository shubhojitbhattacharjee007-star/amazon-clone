package com.amazonclone.mapper;

import com.amazonclone.dto.CartItemResponse;
import com.amazonclone.dto.CartResponse;
import com.amazonclone.entity.Cart;
import com.amazonclone.entity.CartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CartMapper {

    public CartResponse toResponse(Cart cart) {
        List<CartItemResponse> items = cart.getItems().stream()
                .map(this::toItemResponse)
                .toList();

        BigDecimal total = items.stream()
                .map(CartItemResponse::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CartResponse.builder()
                .id(cart.getId())
                .items(items)
                .total(total)
                .updatedAt(cart.getUpdatedAt())
                .build();
    }

    public CartItemResponse toItemResponse(CartItem item) {
        BigDecimal subtotal = item.getProduct().getPrice()
                .multiply(BigDecimal.valueOf(item.getQuantity()));

        return CartItemResponse.builder()
                .id(item.getId())
                .productId(item.getProduct().getId())
                .productName(item.getProduct().getName())
                .productPrice(item.getProduct().getPrice())
                .productImageUrl(item.getProduct().getImageUrl())
                .stockQuantity(item.getProduct().getStockQuantity())
                .quantity(item.getQuantity())
                .subtotal(subtotal)
                .build();
    }
}
