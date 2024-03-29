package com.sensor.repository;

import com.sensor.entity.Cart;
import com.sensor.enums.CartState;
import com.sensor.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findFirstByUser(User user);

    List<Cart> findByState(CartState state);
    List<Cart> findByUserAndState(User user, CartState state);
}
