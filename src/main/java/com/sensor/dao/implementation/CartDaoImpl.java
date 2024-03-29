package com.sensor.dao.implementation;

import com.sensor.dao.ICartDao;
import com.sensor.entity.Cart;
import com.sensor.enums.CartState;
import com.sensor.repository.ICartRepository;
import com.sensor.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CartDaoImpl implements ICartDao {

    @Autowired
    private ICartRepository cartRepository;

    @Override
    public Optional<Cart> getCartByUser(User user) {
        return this.cartRepository.findFirstByUser(user);
    }

    @Override
    public void saveCart(Cart cart) {
        this.cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Cart cart) {
        this.cartRepository.delete(cart);
    }

    @Override
    public Optional<Cart> getCartById(Long id) {
        return this.cartRepository.findById(id);
    }

}
