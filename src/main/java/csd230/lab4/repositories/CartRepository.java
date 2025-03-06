package csd230.lab4.repositories;

import csd230.lab4.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findById(long id);
}
