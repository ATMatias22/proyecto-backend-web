package com.sensor.service;

import com.sensor.entity.Cart;
import com.sensor.entity.CartProduct;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartInfoTransportToService;

import java.util.List;

public interface ICartService {

    CartInfoTransportToController getCartThatAreNotTerminadoOrEntregaByUserLoggedIn();

    void saveCart(Cart cart);

    CartInfoTransportToController changeState(CartInfoTransportToService cartInfoTransportToService);

    CartProduct addProduct(Long idProduct, Double quantity);
    CartProduct removeProduct(Long idProduct, Double quantity);

    List<Cart> getAllCartsWhereTheStatusIsTerminadoByUserLoggedIn();
    List<Cart> getAllCartsWhereTheStatusIsEntregaByUserLoggedIn();


}
