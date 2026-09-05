package com.amazonclone.dto;

import com.amazonclone.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private UUID id;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private List<OrderItemResponse> items;
    private OffsetDateTime createdAt;

    public static class OrderResponseBuilder {

        public OrderResponseBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt == null
                    ? null
                    : createdAt.atOffset(ZoneOffset.UTC);
            return this;
        }
    }
}
