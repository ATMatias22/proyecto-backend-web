package com.sensor.service;

import com.sensor.entity.Cart;
import com.sensor.entity.CartProduct;
import com.sensor.entity.Product;

public interface ICartProductService {

    CartProduct getCartProductByProductAndCart(Product product, Cart cart);

    boolean existsCartProductByCart(Cart cart);

    CartProduct saveCartProduct(CartProduct cartProduct);

    void deleteCartProduct(CartProduct cartProduct);

    void deleteCartProductByCart(Cart cart);


}
