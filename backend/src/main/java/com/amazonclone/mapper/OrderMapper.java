package com.amazonclone.mapper;

import com.amazonclone.dto.OrderItemResponse;
import com.amazonclone.dto.OrderResponse;
import com.amazonclone.entity.Order;
import com.amazonclone.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public OrderResponse toResponse(Order order) {
        List<OrderItemResponse> items = order.getOrderItems() == null
                ? List.of()
                : order.getOrderItems().stream()
                        .map(this::toItemResponse)
                        .toList();

        return OrderResponse.builder()
                .id(order.getId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .items(items)
                .createdAt(order.getCreatedAt())
                .build();
    }

    public OrderItemResponse toItemResponse(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProduct().getId())
                .productName(orderItem.getProductName())
                .unitPrice(orderItem.getUnitPrice())
                .quantity(orderItem.getQuantity())
                .subtotal(orderItem.getSubtotal())
                .build();
    }
}
