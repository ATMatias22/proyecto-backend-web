package com.sensor.pattern.cart.strategy;

import com.sensor.dao.ICartDao;
import com.sensor.entity.*;
import com.sensor.enums.CartState;
import com.sensor.enums.StockState;
import com.sensor.exception.GeneralException;
import com.sensor.security.entity.User;
import com.sensor.service.ICartProductService;
import com.sensor.service.IProductService;
import com.sensor.service.IShippingMethodService;
import com.sensor.service.IStockService;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartInfoTransportToService;
import com.sensor.utils.transport.cart.CartTransportToController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CartInitialStateStrategy extends CartStateStrategy {

    @Autowired
    private ICartDao cartDao;

    @Autowired
    private IShippingMethodService shippingMethodService;

    @Autowired
    private ICartProductService cartProductService;

    @Autowired
    private IStockService stockService;

    @Autowired
    private IProductService productService;


    @Override
    public CartState getState() {
        return CartState.ESTADO_INICIAL;
    }

    @Override
    public CartState getPreviousState() {
        return null;
    }

    @Override
    public CartState getNextState() {
        return CartState.CHECKOUT;
    }

    @Override
    public CartInfoTransportToController getCartInfo(User user, CartTransportToController cart) {
        return new CartInfoTransportToController(cart, null, null, null);
    }

    @Override
    public void verifyNecessaryData(CartInfoTransportToService cartDataRequest) {
        // En este estado no es necesario enviar informacion, por eso el CartInfoTransportToService vendra con sus datos en null.
    }

    @Override
    @Transactional
    public CartInfoTransportToController changeState(User user, CartTransportToController cartTransport, Cart cart, CartInfoTransportToService cartDataRequest) {
        System.out.println("Estoy en el estado de inicio y pronto en checkout");

        if(!this.cartProductService.existsCartProductByCart(cart)){
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Debes agregar almenos un producto al carrito antes de pasar a la siguiente etapa");
        }
        cart.setState(getNextState());
        this.cartDao.saveCart(cart);
        cartTransport.getCart().setState(getNextState());
        return this.nextDataToReturn(user, cartTransport);
    }

    @Override
    protected CartInfoTransportToController nextDataToReturn(User user, CartTransportToController cart) {
        List<ShippingMethod> shippingMethods = this.shippingMethodService.getAllShippingMethods();

        return new CartInfoTransportToController(cart, shippingMethods, null, user.getAddress());
    }

    @Override
    public CartProduct addProduct(Long productId, int quantity, User user, Cart cart) {

        Product product = this.productService.getEnabledProductByIdWithoutBase64Image(productId);
        int quantityAvaibleStock = this.stockService.getAvailableStockQuantityByProduct(product);
        //verificamos que haya stock
        if (quantityAvaibleStock < quantity) {
            throw new GeneralException(HttpStatus.BAD_REQUEST, "La cantidad de stock disponible es de: " + quantityAvaibleStock + " y se esta queriendo agregar al carrito " + quantity);
        }
        List<Stock> stocks = this.stockService.getNAvailableStockQuantityByProduct(product, quantity);

        stocks.forEach( stock -> {
            stock.setStockState(StockState.EN_CARRITO);
            stock.setCart(cart);
        });

        CartProduct cartProduct = null;
        try {
            cartProduct = this.cartProductService.getCartProductByProductAndCart(product, cart);
            cartProduct.setQuantity( cartProduct.getQuantity() + quantity);
        } catch (GeneralException ge) {
            cartProduct = new CartProduct(cart, product, quantity);
        }finally {
            this.stockService.saveStockIterable(stocks);
            //guardamos el producto en el carrito
            cartProduct = this.cartProductService.saveCartProduct(cartProduct);
        }

        return  cartProduct;

    }

    @Override
    @Transactional
    public CartProduct removeProduct(Long productId, int quantity, User user, Cart cart) {

        Product product = this.productService.getEnabledProductByIdWithoutBase64Image(productId);

        CartProduct cartProduct = this.cartProductService.getCartProductByProductAndCart(product, cart);

        int currentQuantityProduct = cartProduct.getQuantity();

        //verificamos que haya stock
        if (currentQuantityProduct < quantity) {
            throw new GeneralException(HttpStatus.BAD_REQUEST, "La cantidad del producto que tiene actualmente es: " + currentQuantityProduct + " unidades y usted quiere sacar " + quantity + " unidades");
        } else if (currentQuantityProduct == quantity) {
            this.cartProductService.deleteCartProductByCart(cart);
            cartProduct = new CartProduct(cartProduct.getCart(),cartProduct.getProduct(),0);
        }else{
            cartProduct.setQuantity(cartProduct.getQuantity() - quantity);
            cartProduct = this.cartProductService.saveCartProduct(cartProduct);
        }

        List<Stock> stocks = this.stockService.getNAvailableStockQuantityByProductAndCart(product, cart, quantity);

        stocks.forEach( stock -> {
            stock.setStockState(StockState.DISPONIBLE);
            stock.setCart(null);
        });

        //guardamos para actualizar el stock
        this.stockService.saveStockIterable(stocks);

        return cartProduct;

    }

    @Override
    public void cancel(Cart cart) {
        throw new GeneralException(HttpStatus.BAD_REQUEST, "No se puede cancelar en el estado: "+ this.getState());
    }

    @Override
    public String getPreferenceId(Cart cart) {
        throw new GeneralException(HttpStatus.BAD_REQUEST, "No se puede obtener una preferencia en el estado: "+ this.getState());
    }

    @Override
    public void preferenceNotification(Cart cart, User userLoggedIn) {
        throw new GeneralException(HttpStatus.BAD_REQUEST, "No se puede obtener una notificacion en el estado: "+ this.getState());
    }
}
