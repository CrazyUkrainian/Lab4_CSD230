package csd230.lab4.controllers;

import csd230.lab4.entities.Cart;
import csd230.lab4.repositories.CartRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/cart")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartRestController {
    private final CartRepository cartRepository;

    public CartRestController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));
    }

    @PostMapping
    public Cart addCart(@RequestBody Cart newCart) {
        return cartRepository.save(newCart);
    }

    @PutMapping("/{id}")
    public Cart updateCart(@RequestBody Cart newCart, @PathVariable Long id) {
        return cartRepository.findById(id)
                .map(cart -> {
                    cart.setItems(newCart.getItems()); // Assuming Cart has a List<CartItem>
                    return cartRepository.save(cart);
                })
                .orElseGet(() -> cartRepository.save(newCart));
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartRepository.deleteById(id);
    }
}
