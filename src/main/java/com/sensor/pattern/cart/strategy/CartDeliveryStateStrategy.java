package com.sensor.pattern.cart.strategy;

import com.sensor.dao.ICartDao;
import com.sensor.entity.*;
import com.sensor.enums.CartState;
import com.sensor.enums.SaleOrderState;
import com.sensor.exception.GeneralException;
import com.sensor.security.entity.User;
import com.sensor.service.ISaleOrderService;
import com.sensor.service.ITemporaryCartAddressService;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartInfoTransportToService;
import com.sensor.utils.transport.cart.CartTransportToController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CartDeliveryStateStrategy extends CartStateStrategy {

    @Autowired
    private ICartDao cartDao;

    @Autowired
    private ISaleOrderService saleOrderService;

    @Override
    public void verifyNecessaryData(CartInfoTransportToService cartDataRequest) {
        // En este estado no es necesario enviar informacion, por eso el CartInfoTransportToService vendra con sus datos en null.
    }

    @Override
    public CartState getState() {
        return CartState.ENTREGA;
    }

    @Override
    protected CartState getPreviousState() {
        return CartState.PAGO;
    }

    @Override
    protected CartState getNextState() {
        return CartState.TERMINADO;
    }

    @Override
    public CartInfoTransportToController getCartInfo(User user, CartTransportToController cart) {
        //devolvemos lo necesario del carrito en este estado.
        //no tendria que ejecutarse nunca este metodo porque apenas llegue al estado ENTREGA, ya existira
        //otro carrito con estado inicial
        throw new GeneralException(HttpStatus.BAD_REQUEST, "No se puede obtener el carrito en estado: "+ this.getState());
    }

    @Override
    public CartInfoTransportToController changeState(User user, CartTransportToController cartTransport, Cart cart, CartInfoTransportToService cartDataRequest) {
        System.out.println("Estoy en el estado de entrega y pronto en terminado");


        return this.nextDataToReturn(user,cartTransport);
    }

    @Override
    protected CartInfoTransportToController nextDataToReturn(User user, CartTransportToController cart) {
        //devolvemos lo necesario del carrito en este estado.
        //no tendria que ejecutarse nunca este metodo porque apenas llegue al estado ENTREGA, ya existira
        //otro carrito con estado inicial
        return new CartInfoTransportToController(null,null,null,null);
    }

    @Override
    public CartProduct addProduct(Long productId, double quantity, User user, Cart cart) {
        throw new GeneralException(HttpStatus.BAD_REQUEST, "No se puede agregar un producto al carrito en el estado: "+ getState() + " tendrias que cancelar el proceso de compra, o terminar el proceso");
    }

    @Override
    public CartProduct removeProduct(Long productId, double quantity, User user, Cart cart) {
        throw new GeneralException(HttpStatus.BAD_REQUEST, "No se puede eliminar un producto del carrito en el estado: "+ getState() + " tendrias que cancelar el proceso de compra");
    }

    @Override
    public void cancel(Cart cart) {
        throw new GeneralException(HttpStatus.BAD_REQUEST, "No se puede cancelar en el estado: "+ this.getState());

    }


}
