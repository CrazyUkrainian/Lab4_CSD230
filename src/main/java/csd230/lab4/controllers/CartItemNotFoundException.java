package csd230.lab4.controllers;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(Long id) {
        super("Could not find CartItem with ID " + id);
    }
}
