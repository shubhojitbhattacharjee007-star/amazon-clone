package com.amazonclone.repository;

import com.amazonclone.entity.Cart;
import com.amazonclone.entity.CartItem;
import com.amazonclone.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    Optional<CartItem> findByCartAndProductId(Cart cart, UUID productId);

    List<CartItem> findByCart(Cart cart);

    Optional<CartItem> findByIdAndCartId(UUID id, UUID cartId);

    @Query("""
            SELECT ci FROM CartItem ci
            JOIN FETCH ci.product
            WHERE ci.cart = :cart
            """)
    List<CartItem> findByCartWithProduct(@Param("cart") Cart cart);
}
