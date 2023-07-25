package com.sensor.pattern.cart.strategy;

import com.sensor.entity.Cart;
import com.sensor.entity.ShippingMethod;
import com.sensor.enums.CartState;
import com.sensor.security.entity.User;
import com.sensor.service.IShippingMethodService;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartTransportToController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartCheckoutStateStrategy implements CartStateStrategy{

    @Autowired
    private IShippingMethodService shippingMethodService;

    @Override
    public CartState getState() {
        return CartState.CHECKOUT;
    }

    @Override
    public CartInfoTransportToController getCartInfo(User user, CartTransportToController cart) {
        List<ShippingMethod> shippingMethods = this.shippingMethodService.getAllShippingMethods();

        return new CartInfoTransportToController(cart, shippingMethods, null, user.getAddress());
    }
}
