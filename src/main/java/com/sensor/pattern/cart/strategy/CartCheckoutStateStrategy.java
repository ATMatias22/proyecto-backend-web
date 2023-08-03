package com.sensor.pattern.cart.strategy;

import com.sensor.dao.ICartDao;
import com.sensor.entity.*;
import com.sensor.enums.CartState;
import com.sensor.exception.GeneralException;
import com.sensor.security.entity.User;
import com.sensor.service.*;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartInfoTransportToService;
import com.sensor.utils.transport.cart.CartTransportToController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CartCheckoutStateStrategy extends CartStateStrategy{

    @Autowired
    private IShippingMethodService shippingMethodService;

    @Autowired
    private ICartAddressService cartAddressService;

    @Autowired
    private ITypeOfAddressService typeOfAddressService;

    @Autowired
    private IPaymentMethodService paymentMethodService;

    @Autowired
    private ICartDao cartDao;


    @Override
    public void verifyNecessaryData(CartInfoTransportToService cartDataRequest) {
        if(cartDataRequest.getChosenAddresses() == null){
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Debe colocar una direccion");
        }
        cartDataRequest.getChosenAddresses().forEach(chosenAddress -> {
            if(chosenAddress.getTypeOfAddress() == null){
                throw new GeneralException(HttpStatus.BAD_REQUEST, "Debe colocar un tipo de direccion valido");
            }
        });
        if(cartDataRequest.getChosenShippingMethod() == null){
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Debe colocar una metodo de envio");
        }
    }

    @Override
    public CartState getState() {
        return CartState.CHECKOUT;
    }

    @Override
    public CartState getPreviousState() {
        return CartState.ESTADO_INICIAL;
    }

    @Override
    public CartState getNextState() {
        return CartState.PAGO;
    }

    @Override
    public CartInfoTransportToController getCartInfo(User user, CartTransportToController cart) {
        List<ShippingMethod> shippingMethods = this.shippingMethodService.getAllShippingMethods();

        return new CartInfoTransportToController(cart, shippingMethods, null, user.getAddress());
    }

    @Override
    @Transactional
    public CartInfoTransportToController changeState(User user, CartTransportToController cartTransport, Cart cart, CartInfoTransportToService cartDataRequest) {
        System.out.println("Estoy en el estado de checkout y pronto en Pago");

        ShippingMethod shippingMethod = this.shippingMethodService.getShippingMethodByName(cartDataRequest.getChosenShippingMethod().getName());
        Set<CartAddress> addresses = new HashSet<>();

      cartDataRequest.getChosenAddresses().forEach( choosenAddress -> {
            //en caso de no existe el tipo de direccion se cancela  y no se realiza ningun cambio en el carrito.
            TypeOfAddress typeOfAddress = typeOfAddressService.getTypeOfAddressByName(choosenAddress.getTypeOfAddress().getName());
            CartAddress cartAddress = new CartAddress();
            cartAddress.setStreet(choosenAddress.getStreet());
            cartAddress.setStreetNumber(choosenAddress.getStreetNumber());
            cartAddress.setFloor(choosenAddress.getFloor());
            cartAddress.setApartmentNumber(choosenAddress.getApartmentNumber());
            cartAddress.setTypeOfAddress(typeOfAddress.getName());
            cartAddress.setCart(cart);
            this.cartAddressService.saveCartAddress(cartAddress);
            addresses.add(cartAddress);
        } );

        //Agregamos al carrito los datos que nos trae (metodo de envio y a que direccioens)
        cart.setShippingMethod(shippingMethod);
        cart.setState(getNextState());

        //guardamos el carrito con esta informacion
        this.cartDao.saveCart(cart);

        //le seteamos al carrito que ira al controller y que tiene las imagenes en base 64 los mismos datos de direcciones y metodo de envio.
        cartTransport.getCart().setState(getNextState());
        cartTransport.getCart().setCartAddresses(addresses);
        cartTransport.getCart().setShippingMethod(shippingMethod);


        return this.nextDataToReturn(user,cartTransport);

    }

    @Override
    protected CartInfoTransportToController nextDataToReturn(User user, CartTransportToController cart) {
        List<PaymentMethod> paymentMethods = this.paymentMethodService.getAllPaymentMethods();
        return new CartInfoTransportToController(cart,null,paymentMethods,null);
    }

    @Override
    public CartProduct addProduct(Long productId, double quantity, User user, Cart cart) {
        throw new GeneralException(HttpStatus.BAD_REQUEST, "No se puede agregar un producto al carrito en el estado: "+ getState() + " tendrias que cancelar el proceso de compra, o terminar el proceso");
    }

    @Override
    public CartProduct removeProduct(Long productId, double quantity, User user, Cart cart) {
        throw new GeneralException(HttpStatus.BAD_REQUEST, "No se puede eliminar un producto del carrito en el estado: "+ getState() + " tendrias que cancelar el proceso de compra");
    }
}
