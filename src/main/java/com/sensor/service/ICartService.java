package com.sensor.service;

import com.sensor.entity.Cart;
import com.sensor.entity.CartProduct;
import com.sensor.external.dto.webhook.MercadoPagoWebhookDTO;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartInfoTransportToService;

import java.util.List;

public interface ICartService {

    CartInfoTransportToController getCartThatAreNotTerminadoOrEntregaByUserLoggedIn();

    CartInfoTransportToController changeState(CartInfoTransportToService cartInfoTransportToService);

    CartProduct addProduct(Long idProduct, Double quantity);
    CartProduct removeProduct(Long idProduct, Double quantity);
    void cancelCart();
    String getPreferenceId();

    void preferenceNotification(MercadoPagoWebhookDTO mercadoPagoWebhookDTO);


}
