package com.sensor.service.implementation;

import com.sensor.dao.ICartProductDao;
import com.sensor.entity.Cart;
import com.sensor.entity.CartProduct;
import com.sensor.entity.Product;
import com.sensor.exception.GeneralException;
import com.sensor.service.ICartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CartProductServiceImpl implements ICartProductService {

    @Autowired
    private ICartProductDao cartProductDao;

    @Override
    public CartProduct getCartProductByProductAndCart(Product product, Cart cart) {
        return this.cartProductDao.getCartProductByCartAndProduct(cart,product).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro el producto en el carrito"));
    }

    @Override
    public boolean existsCartProductByCart(Cart cart) {
        return this.cartProductDao.existsCartProductByCart(cart);
    }

    @Override
    public CartProduct saveCartProduct(CartProduct cartProduct) {
        return this.cartProductDao.saveCartProduct(cartProduct);
    }

    @Override
    public void deleteCartProduct(CartProduct cartProduct) {
        this.cartProductDao.deleteCartProduct(cartProduct);
    }
}
