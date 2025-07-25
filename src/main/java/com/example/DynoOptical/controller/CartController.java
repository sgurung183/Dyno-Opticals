package com.example.DynoOptical.controller;

import com.example.DynoOptical.model.Cart;
import com.example.DynoOptical.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add 1 item to cart
    @PostMapping("/{customerId}/add/{itemId}")
    public ResponseEntity<Cart> addItem(@PathVariable Long customerId, @PathVariable Long itemId) {
        Cart saved = cartService.addItemToCart(customerId, itemId);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    // Remove 1 quantity of the item from the cart
    @DeleteMapping("/{customerId}/remove-one/{itemId}")
    public ResponseEntity<Cart> removeOne(@PathVariable Long customerId, @PathVariable Long itemId) {
        Cart updated = cartService.removeFromCart(customerId, itemId);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Remove the entire line item (all quantities)
    @DeleteMapping("/{customerId}/remove-all/{itemId}")
    public ResponseEntity<Cart> removeLine(@PathVariable Long customerId, @PathVariable Long itemId) {
        Cart updated = cartService.removeLine(customerId, itemId);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Get total cart value
    @GetMapping("/{customerId}/total")
    public ResponseEntity<Double> getTotal(@PathVariable Long customerId) {
        double total = cartService.calculateCartTotal(customerId);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
