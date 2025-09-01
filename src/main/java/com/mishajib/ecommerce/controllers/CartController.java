package com.mishajib.ecommerce.controllers;

import com.mishajib.ecommerce.dtos.AddItemToCartRequest;
import com.mishajib.ecommerce.dtos.CartDto;
import com.mishajib.ecommerce.dtos.CartItemDto;
import com.mishajib.ecommerce.dtos.UpdateCartItemRequest;
import com.mishajib.ecommerce.entities.Cart;
import com.mishajib.ecommerce.entities.CartItem;
import com.mishajib.ecommerce.mappers.CartMapper;
import com.mishajib.ecommerce.repositories.CartRepository;
import com.mishajib.ecommerce.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<CartDto> createCart() {
        var cart = new Cart();
        cartRepository.save(cart);

        var cartDto = cartMapper.toDto(cart);

        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(
            @PathVariable UUID cartId,
            @Valid @RequestBody AddItemToCartRequest request
    ) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);

        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        var product = productRepository.findById(request.getProductId()).orElse(null);
        if (product == null) {
            return ResponseEntity.badRequest().build();
        }

        var cartItem = cart.addItem(product);

        cartRepository.save(cart);

        var cartItemDto = cartMapper.toDto(cartItem);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cartMapper.toDto(cart));
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> updateCartItem(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("productId") Long productId,
            @Valid @RequestBody UpdateCartItemRequest request
    ) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);

        if (cart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "Cart not found!")
            );
        }

        var cartItem = cart.getItem(productId);

        if (cartItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "Product not found in the cart!")
            );
        }

        cartItem.setQuantity(request.getQuantity());
        cartRepository.save(cart);

        return ResponseEntity.ok(cartMapper.toDto(cartItem));
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> removeCartItem(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("productId") Long productId
    ) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "Cart not found!")
            );
        }

        var cartItem = cart.getItem(productId);
        if (cartItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "Product not found in the cart!")
            );
        }

        cart.getItems().remove(cartItem);
        cartItem.setCart(null);
        cartRepository.save(cart);

        return ResponseEntity.status(HttpStatus.OK).body(
                Map.of("message", "Product removed from the cart successfully.")
        );
    }
}
