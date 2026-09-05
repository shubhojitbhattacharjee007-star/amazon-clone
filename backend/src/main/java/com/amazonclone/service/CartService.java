package com.amazonclone.service;

import com.amazonclone.dto.AddCartItemRequest;
import com.amazonclone.dto.CartResponse;
import com.amazonclone.dto.UpdateCartItemRequest;
import com.amazonclone.entity.Cart;
import com.amazonclone.entity.CartItem;
import com.amazonclone.entity.Product;
import com.amazonclone.entity.User;
import com.amazonclone.exception.InactiveProductException;
import com.amazonclone.exception.InsufficientStockException;
import com.amazonclone.exception.ResourceNotFoundException;
import com.amazonclone.mapper.CartMapper;
import com.amazonclone.repository.CartItemRepository;
import com.amazonclone.repository.CartRepository;
import com.amazonclone.repository.ProductRepository;
import com.amazonclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;

    @Transactional(readOnly = true)
    public CartResponse getCart() {
        Cart cart = getOrCreateCartWithItems(getCurrentUser());
        return cartMapper.toResponse(cart);
    }

    @Transactional
    public CartResponse addItem(AddCartItemRequest request) {
        User user = getCurrentUser();
        Product product = getActiveProduct(request.getProductId());

        Cart cart = getOrCreateCart(user);

        CartItem existingItem = cartItemRepository
                .findByCartAndProductId(cart, product.getId())
                .orElse(null);

        int newQuantity = existingItem == null
                ? request.getQuantity()
                : existingItem.getQuantity() + request.getQuantity();

        validateStock(product, newQuantity);

        if (existingItem != null) {
            existingItem.setQuantity(newQuantity);
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();

            cart.getItems().add(newItem);
        }

        Cart savedCart = cartRepository.save(cart);

        return cartMapper.toResponse(
                loadCartWithItems(savedCart.getId())
        );
    }

    @Transactional
    public CartResponse updateItemQuantity(
            UUID itemId,
            UpdateCartItemRequest request
    ) {
        User user = getCurrentUser();
        Cart cart = getCartForUser(user);

        CartItem item = cartItemRepository
                .findByIdAndCartId(itemId, cart.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart item", itemId)
                );

        Product product = item.getProduct();

        validateActiveProduct(product);
        validateStock(product, request.getQuantity());

        item.setQuantity(request.getQuantity());

        cartRepository.save(cart);

        return cartMapper.toResponse(
                loadCartWithItems(cart.getId())
        );
    }

    @Transactional
    public CartResponse removeItem(UUID itemId) {
        User user = getCurrentUser();
        Cart cart = getCartForUser(user);

        CartItem item = cartItemRepository
                .findByIdAndCartId(itemId, cart.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart item", itemId)
                );

        cart.getItems().remove(item);

        cartRepository.save(cart);

        return cartMapper.toResponse(
                loadCartWithItems(cart.getId())
        );
    }

    @Transactional
    public CartResponse clearCart() {
        User user = getCurrentUser();
        Cart cart = getOrCreateCart(user);

        cart.getItems().clear();

        cartRepository.save(cart);

        return cartMapper.toResponse(cart);
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

    private Cart getOrCreateCart(User user) {
        return cartRepository.findByUserId(user.getId())
                .orElseGet(() ->
                        cartRepository.save(
                                Cart.builder()
                                        .user(userRepository.getReferenceById(user.getId()))
                                        .build()
                        )
                );
    }

    private Cart getOrCreateCartWithItems(User user) {
        return cartRepository.findByUserIdWithItems(user.getId())
                .orElseGet(() -> getOrCreateCart(user));
    }

    private Cart getCartForUser(User user) {
        return cartRepository.findByUserId(user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart", user.getId())
                );
    }

    private Cart loadCartWithItems(UUID cartId) {
        return cartRepository.findByIdWithItems(cartId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart", cartId)
                );
    }

    private Product getActiveProduct(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", productId)
                );

        validateActiveProduct(product);

        return product;
    }

    private void validateActiveProduct(Product product) {
        if (!product.isActive()) {
            throw new InactiveProductException(product.getName());
        }
    }

    private void validateStock(
            Product product,
            int requestedQuantity
    ) {
        if (requestedQuantity > product.getStockQuantity()) {
            throw new InsufficientStockException(
                    product.getName(),
                    requestedQuantity,
                    product.getStockQuantity()
            );
        }
    }
}
