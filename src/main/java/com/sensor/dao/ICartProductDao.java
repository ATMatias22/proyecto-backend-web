package com.sensor.dao;

import com.sensor.entity.Cart;
import com.sensor.entity.CartProduct;
import com.sensor.entity.Product;

import java.util.Optional;

public interface ICartProductDao {

    CartProduct saveCartProduct(CartProduct cartProduct);

    Optional<CartProduct> getCartProductByCartAndProduct(Cart cart, Product product);

    boolean existsCartProductByCart(Cart cart);

    void deleteCartProduct(CartProduct cartProduct);

    void deleteCartProductByCart(Cart cart);


}
