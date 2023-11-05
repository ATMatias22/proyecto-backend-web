package com.sensor.service;

import com.sensor.entity.Cart;
import com.sensor.entity.CartProduct;
import com.sensor.external.dto.webhook.MercadoPagoWebhookDTO;
import com.sensor.security.entity.User;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartInfoTransportToService;

public interface ICartService {

    CartInfoTransportToController getCartByUserLoggedIn(User userLoggedIn);

    CartInfoTransportToController changeState(CartInfoTransportToService cartInfoTransportToService, User userLoggedIn);

    CartProduct addProduct(Long idProduct, int quantity, User userLoggedIn);
    CartProduct removeProduct(Long idProduct, Double quantity, User userLoggedIn);
    void cancelCart(User userLoggedIn);
    String getPreferenceId(User userLoggedIn);

    void saveCart(Cart cart);

    void preferenceNotification(MercadoPagoWebhookDTO mercadoPagoWebhookDTO);

    void deleteCart(User userLoggedIn);



}
