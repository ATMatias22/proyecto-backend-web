package com.sensor.pattern.cart.strategy;

import com.sensor.entity.Cart;
import com.sensor.enums.CartState;
import com.sensor.security.entity.User;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartTransportToController;
import org.springframework.stereotype.Component;

@Component
public class CartInitialStateStrategy implements CartStateStrategy{
    @Override
    public CartState getState() {
        return CartState.ESTADO_INICIAL;
    }
    @Override
    public CartInfoTransportToController getCartInfo(User user, CartTransportToController cart) {
        return new CartInfoTransportToController(cart, null,null,null);
    }
}
