package com.amazonclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {

    private UUID id;
    private UUID productId;
    private String productName;
    private BigDecimal productPrice;
    private String productImageUrl;
    private Integer stockQuantity;
    private Integer quantity;
    private BigDecimal subtotal;
}
