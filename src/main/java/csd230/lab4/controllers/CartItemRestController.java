package csd230.lab4.controllers;

import csd230.lab4.entities.CartItem;
import csd230.lab4.repositories.CartItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/cartitem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartItemRestController {
    private final CartItemRepository cartItemRepository;

    public CartItemRestController(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public CartItem getCartItem(@PathVariable Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new CartItemNotFoundException(id));
    }

    @PostMapping
    public CartItem addCartItem(@RequestBody CartItem newCartItem) {
        return cartItemRepository.save(newCartItem);
    }

    @PutMapping("/{id}")
    public CartItem updateCartItem(@RequestBody CartItem newCartItem, @PathVariable Long id) {
        return cartItemRepository.findById(id)
                .map(cartItem -> {
                    cartItem.setId(newCartItem.getId());
                    cartItem.setQuantity(newCartItem.getQuantity());
                    cartItem.setPrice(newCartItem.getPrice());
                    return cartItemRepository.save(cartItem);
                })
                .orElseGet(() -> cartItemRepository.save(newCartItem));
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartItemRepository.deleteById(id);
    }
}
