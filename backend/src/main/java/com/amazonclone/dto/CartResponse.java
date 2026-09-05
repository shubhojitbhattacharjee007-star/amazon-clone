package com.amazonclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    private UUID id;
    private List<CartItemResponse> items;
    private BigDecimal total;
    private Instant updatedAt;
}
