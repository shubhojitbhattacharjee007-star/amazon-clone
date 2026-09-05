package com.amazonclone.repository;

import com.amazonclone.entity.Cart;
import com.amazonclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {

    Optional<Cart> findByUser(User user);

    Optional<Cart> findByUserId(UUID userId);

    @Query("""
            SELECT DISTINCT c FROM Cart c
            LEFT JOIN FETCH c.items i
            LEFT JOIN FETCH i.product
            WHERE c.user.id = :userId
            """)
    Optional<Cart> findByUserIdWithItems(@Param("userId") UUID userId);

    @Query("""
            SELECT DISTINCT c FROM Cart c
            LEFT JOIN FETCH c.items i
            LEFT JOIN FETCH i.product
            WHERE c.id = :cartId
            """)
    Optional<Cart> findByIdWithItems(@Param("cartId") UUID cartId);
}
