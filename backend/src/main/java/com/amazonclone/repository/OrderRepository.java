package com.amazonclone.repository;

import com.amazonclone.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByUserId(UUID userId);

    @Query("""
            SELECT DISTINCT o FROM Order o
            LEFT JOIN FETCH o.orderItems oi
            LEFT JOIN FETCH oi.product
            WHERE o.user.id = :userId
            ORDER BY o.createdAt DESC
            """)
    List<Order> findByUserIdWithItemsOrderByCreatedAtDesc(
            @Param("userId") UUID userId
    );

    @Query("""
            SELECT DISTINCT o FROM Order o
            LEFT JOIN FETCH o.orderItems oi
            LEFT JOIN FETCH oi.product
            WHERE o.id = :orderId AND o.user.id = :userId
            """)
    Optional<Order> findByIdAndUserIdWithItems(
            @Param("orderId") UUID orderId,
            @Param("userId") UUID userId
    );
}
