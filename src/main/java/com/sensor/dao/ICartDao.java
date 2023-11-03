package com.sensor.dao;

import com.sensor.entity.Cart;
import com.sensor.security.entity.User;

import java.util.List;
import java.util.Optional;

public interface ICartDao {

    Optional<Cart> getCartByUser(User user);

    void saveCart(Cart cart);

    void deleteCart(Cart cart);

    Optional<Cart> getCartById(Long id);

}
