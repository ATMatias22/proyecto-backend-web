package com.sensor.service;

import com.sensor.utils.transport.cart.CartInfoTransportToController;

public interface ICartService {

    CartInfoTransportToController getCartThatAreNotTerminadoByUserLoggedIn();

}
