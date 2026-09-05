package com.amazonclone.controller;

import com.amazonclone.dto.OrderResponse;
import com.amazonclone.dto.UpdateOrderStatusRequest;
import com.amazonclone.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getMyOrders() {
        return ResponseEntity.ok(orderService.getMyOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getMyOrderDetails(
            @PathVariable UUID orderId
    ) {
        return ResponseEntity.ok(orderService.getMyOrderDetails(orderId));
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<OrderResponse> cancelMyOrder(
            @PathVariable UUID orderId
    ) {
        return ResponseEntity.ok(orderService.cancelMyOrder(orderId));
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable UUID orderId,
            @Valid @RequestBody UpdateOrderStatusRequest request
    ) {
        return ResponseEntity.ok(
                orderService.updateOrderStatus(orderId, request)
        );
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createOrder());
    }
}
