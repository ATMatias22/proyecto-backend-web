package com.sensor.pattern.cart.strategy;

import com.sensor.entity.Cart;
import com.sensor.entity.PaymentMethod;
import com.sensor.enums.CartState;
import com.sensor.security.entity.User;
import com.sensor.service.IPaymentMethodService;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartTransportToController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartPaymentStateStrategy implements CartStateStrategy {

    @Autowired
    private IPaymentMethodService paymentMethodService;

    @Override
    public CartState getState() {
        return CartState.PAGO;
    }

    @Override
    public CartInfoTransportToController getCartInfo(User user, CartTransportToController cart) {
        List<PaymentMethod> paymentMethods = this.paymentMethodService.getAllPaymentMethods();

        return new CartInfoTransportToController(cart,null,paymentMethods,null);
    }
}
