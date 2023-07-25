package com.sensor.pattern.cart.strategy;

import com.sensor.entity.Cart;
import com.sensor.enums.CartState;
import com.sensor.security.entity.User;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartTransportToController;

public interface CartStateStrategy {

    CartState getState();
    CartInfoTransportToController getCartInfo(User user, CartTransportToController cart);

}
