package com.sensor.dao;

import com.sensor.entity.Cart;
import com.sensor.security.entity.User;

import java.util.List;
import java.util.Optional;

public interface ICartDao {

    Optional<Cart> getCartByUserAndStateNotInTerminadoOrEntrega(User user);

    void saveCart(Cart cart);

    List<Cart> getAllCartsWhereTheStatusIsTerminado();
    List<Cart> getAllCartsByUserAndWhereTheStatusIsEntrega(User user);
    List<Cart> getAllCartsByUserAndWhereTheStatusIsTerminado(User user);

}
