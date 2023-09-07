package com.sensor.pattern.cart.strategy;

import com.sensor.entity.Cart;
import com.sensor.entity.CartProduct;
import com.sensor.enums.CartState;
import com.sensor.security.entity.User;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartInfoTransportToService;
import com.sensor.utils.transport.cart.CartTransportToController;

public abstract class CartStateStrategy {

    public abstract void verifyNecessaryData(CartInfoTransportToService cartDataRequest);
    public abstract CartState getState();
    protected abstract CartState getPreviousState();
    protected abstract CartState getNextState();
    public abstract CartInfoTransportToController getCartInfo(User user, CartTransportToController cart);
    public abstract CartInfoTransportToController changeState(User user, CartTransportToController cartProducts, Cart cart, CartInfoTransportToService cartDataRequest);
    protected abstract CartInfoTransportToController nextDataToReturn(User user, CartTransportToController cart);
    public abstract CartProduct addProduct(Long productId, double quantity, User user, Cart cart);
    public abstract CartProduct removeProduct(Long productId, double quantity, User user, Cart cart);
    public abstract void cancel(Cart cart);
    public abstract String getPreferenceId(Cart cart, User userLoggedIn);
    public abstract void preferenceNotification(Cart cart, User userLoggedIn);

}
