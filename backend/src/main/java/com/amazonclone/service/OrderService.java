package com.amazonclone.service;

import com.amazonclone.dto.OrderResponse;
import com.amazonclone.dto.UpdateOrderStatusRequest;
import com.amazonclone.entity.Cart;
import com.amazonclone.entity.CartItem;
import com.amazonclone.entity.Order;
import com.amazonclone.entity.OrderItem;
import com.amazonclone.entity.OrderStatus;
import com.amazonclone.entity.Product;
import com.amazonclone.entity.User;
import com.amazonclone.exception.BaseException;
import com.amazonclone.exception.InactiveProductException;
import com.amazonclone.exception.InsufficientStockException;
import com.amazonclone.exception.ResourceNotFoundException;
import com.amazonclone.mapper.OrderMapper;
import com.amazonclone.repository.CartRepository;
import com.amazonclone.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional(readOnly = true)
    public List<OrderResponse> getMyOrders() {
        User user = getCurrentUser();

        return orderRepository
                .findByUserIdWithItemsOrderByCreatedAtDesc(user.getId())
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAllWithItemsOrderByCreatedAtDesc()
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrderResponse getMyOrderDetails(UUID orderId) {
        User user = getCurrentUser();

        Order order = orderRepository
                .findByIdAndUserIdWithItems(orderId, user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order", orderId)
                );

        return orderMapper.toResponse(order);
    }

    @Transactional
    public OrderResponse cancelMyOrder(UUID orderId) {
        User user = getCurrentUser();

        Order order = orderRepository
                .findByIdAndUserIdWithItems(orderId, user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order", orderId)
                );

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new BaseException(
                    "Only pending orders can be cancelled",
                    HttpStatus.BAD_REQUEST,
                    "ORDER_CANCELLATION_NOT_ALLOWED"
            );
        }

        order.getOrderItems().forEach(orderItem -> {
            Product product = orderItem.getProduct();
            product.setStockQuantity(
                    product.getStockQuantity() + orderItem.getQuantity()
            );
        });

        order.setStatus(OrderStatus.CANCELLED);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toResponse(savedOrder);
    }

    @Transactional
    public OrderResponse updateOrderStatus(
            UUID orderId,
            UpdateOrderStatusRequest request
    ) {
        Order order = orderRepository
                .findByIdWithItems(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order", orderId)
                );

        OrderStatus requestedStatus = toOrderStatus(request.getStatus());

        if (!isValidStatusTransition(order.getStatus(), requestedStatus)) {
            throw new BaseException(
                    "Order status transition from %s to %s is not allowed"
                            .formatted(order.getStatus(), requestedStatus),
                    HttpStatus.BAD_REQUEST,
                    "ORDER_STATUS_TRANSITION_NOT_ALLOWED"
            );
        }

        order.setStatus(requestedStatus);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toResponse(savedOrder);
    }

    @Transactional
    public OrderResponse createOrder() {
        User user = getCurrentUser();
        Cart cart = cartRepository.findByUserIdWithItems(user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart", user.getId())
                );

        if (cart.getItems().isEmpty()) {
            throw new BaseException(
                    "Cannot create an order from an empty cart",
                    HttpStatus.BAD_REQUEST,
                    "CART_EMPTY"
            );
        }

        cart.getItems().forEach(this::validateCartItem);

        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.PENDING)
                .totalAmount(BigDecimal.ZERO)
                .build();

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            BigDecimal subtotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .productName(product.getName())
                    .unitPrice(product.getPrice())
                    .quantity(cartItem.getQuantity())
                    .subtotal(subtotal)
                    .build();

            order.getOrderItems().add(orderItem);
            totalAmount = totalAmount.add(subtotal);
            product.setStockQuantity(
                    product.getStockQuantity() - cartItem.getQuantity()
            );
        }

        order.setTotalAmount(totalAmount);
        Order savedOrder = orderRepository.save(order);

        cart.getItems().clear();
        cartRepository.save(cart);

        return orderMapper.toResponse(savedOrder);
    }

    private User getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || !(authentication.getPrincipal() instanceof User user)) {
            throw new AccessDeniedException("User not authenticated");
        }

        return user;
    }

    private void validateCartItem(CartItem cartItem) {
        Product product = cartItem.getProduct();

        if (!product.isActive()) {
            throw new InactiveProductException(product.getName());
        }

        if (cartItem.getQuantity() > product.getStockQuantity()) {
            throw new InsufficientStockException(
                    product.getName(),
                    cartItem.getQuantity(),
                    product.getStockQuantity()
            );
        }
    }

    private boolean isValidStatusTransition(
            OrderStatus currentStatus,
            OrderStatus requestedStatus
    ) {
        return (currentStatus == OrderStatus.PENDING
                && requestedStatus == OrderStatus.CONFIRMED)
                || (currentStatus == OrderStatus.CONFIRMED
                && requestedStatus == OrderStatus.SHIPPED)
                || (currentStatus == OrderStatus.SHIPPED
                && requestedStatus == OrderStatus.DELIVERED);
    }

    private OrderStatus toOrderStatus(String status) {
        try {
            return OrderStatus.valueOf(status);
        } catch (IllegalArgumentException ex) {
            throw new BaseException(
                    "Invalid order status: %s".formatted(status),
                    HttpStatus.BAD_REQUEST,
                    "ORDER_STATUS_INVALID"
            );
        }
    }
}
