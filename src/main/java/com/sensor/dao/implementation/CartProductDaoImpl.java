package com.sensor.dao.implementation;

import com.sensor.dao.ICartProductDao;
import com.sensor.entity.Cart;
import com.sensor.entity.CartProduct;
import com.sensor.entity.Product;
import com.sensor.repository.ICartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CartProductDaoImpl implements ICartProductDao {

    @Autowired
    private ICartProductRepository cartProductRepository;

    @Override
    public CartProduct saveCartProduct(CartProduct cartProduct) {
        return this.cartProductRepository.save(cartProduct);
    }

    @Override
    public Optional<CartProduct> getCartProductByCartAndProduct(Cart cart, Product product) {
        return this.cartProductRepository.findByProductAndCart(product,cart);
    }

    @Override
    public boolean existsCartProductByCart(Cart cart) {
        return this.cartProductRepository.existsByCart(cart);
    }

    @Override
    public void deleteCartProduct(CartProduct cartProduct) {
        this.cartProductRepository.delete(cartProduct);
    }
}
