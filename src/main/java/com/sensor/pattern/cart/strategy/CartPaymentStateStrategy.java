package com.sensor.pattern.cart.strategy;

import com.sensor.dao.ICartDao;
import com.sensor.dao.ICartProductDao;
import com.sensor.entity.*;
import com.sensor.enums.CartState;
import com.sensor.enums.SaleOrderState;
import com.sensor.exception.GeneralException;
import com.sensor.security.entity.User;
import com.sensor.service.*;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartInfoTransportToService;
import com.sensor.utils.transport.cart.CartTransportToController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CartPaymentStateStrategy extends CartStateStrategy {

    @Autowired
    private IPaymentMethodService paymentMethodService;
    @Autowired
    private ITemporaryCartAddressService temporaryCartAddressService;
    @Autowired
    private ICartDao cartDao;

    @Autowired
    private ISaleOrderService saleOrderService;

    @Override
    public CartState getState() {
        return CartState.PAGO;
    }

    @Override
    public CartState getPreviousState() {
        return CartState.CHECKOUT;
    }

    @Override
    public CartState getNextState() {
        return null;
    }

    @Override
    public CartInfoTransportToController getCartInfo(User user, CartTransportToController cart) {
        List<PaymentMethod> paymentMethods = this.paymentMethodService.getAllPaymentMethods();

        return new CartInfoTransportToController(cart, null, paymentMethods, null);
    }

    @Override
    public void verifyNecessaryData(CartInfoTransportToService cartDataRequest) {

        if (cartDataRequest.getChosenPaymentMethod() == null) {
            throw new GeneralException(HttpStatus.BAD_REQUEST, "Debe colocar un metodo de pago");
        }

    }

    @Override
    @Transactional
    public CartInfoTransportToController changeState(User user, CartTransportToController cartTransport, Cart cart, CartInfoTransportToService cartDataRequest) {
        System.out.println("Estoy en el estado de pago y pronto en entregado");
        //cartDataRequest es el dato que viene por parte de la request
        //cart es el carrito en el estado actual
        //cartTransport es el carrito que se devolvera al usuario.
        PaymentMethod paymentMethod = this.paymentMethodService.getPaymentMethodByName(cartDataRequest.getChosenPaymentMethod().getPaymentMethod().getName());

        //le seteamos al carrito que ira al controller y que tiene las imagenes en base 64 los mismos datos de direcciones, metodo de envio y metodode de pago.
        cartTransport.getCart().setPaymentMethod(paymentMethod);

        //se veran todos los datos que se fueron completando.
        CartInfoTransportToController cartInfoTransportToController = this.nextDataToReturn(user, cartTransport);

        //creamos la orden con los datos que tiene el carrito.
        //y guardamos la orden
        this.saleOrderService.saveSaleOrder(this.createSaleOrder(cart));

        //eliminamos el carrito, como los atributos temporaryAddresses, shippingMethod y cartProducts tienen cascade remove, se eliminaran tambien
        //asi que no es necesario eliminarlos a traves de sus servicios
        this.cartDao.deleteCart(cart);

        //Creamos el nuevo carrito para que el usuario pueda realizar mas compras.
        Cart newCartForUser = new Cart();
        newCartForUser.setUser(user);

        this.cartDao.saveCart(newCartForUser);

        return cartInfoTransportToController;
    }

    @Override
    protected CartInfoTransportToController nextDataToReturn(User user, CartTransportToController cart) {
        return new CartInfoTransportToController(cart, null, null, null);
    }

    @Override
    public CartProduct addProduct(Long productId, double quantity, User user, Cart cart) {
        throw new GeneralException(HttpStatus.BAD_REQUEST, "No se puede agregar un producto al carrito en el estado: " + getState() + " tendrias que cancelar el proceso de compra, o terminar el proceso");
    }

    @Override
    public CartProduct removeProduct(Long productId, double quantity, User user, Cart cart) {
        throw new GeneralException(HttpStatus.BAD_REQUEST, "No se puede eliminar un producto del carrito en el estado: " + getState() + " tendrias que cancelar el proceso de compra");
    }

    @Override
    public void cancel(Cart cart) {
        cart.setShippingMethod(null);
        cart.setState(CartState.ESTADO_INICIAL);
        this.temporaryCartAddressService.deleteTemporaryCartAddressByCart(cart);
        this.cartDao.saveCart(cart);
    }

    private SaleOrder createSaleOrder(Cart cart) {

        User user = cart.getUser();
        List<CartProduct> products = cart.getCartProducts();
        Set<TemporaryCartAddress> addresses = cart.getTemporaryCartAddresses();
        PaymentMethod paymentMethod = cart.getPaymentMethod();
        ShippingMethod shippingMethod = cart.getShippingMethod();
        Double subtotal = this.calculateSubtotal(products);
        Double total = this.calculateTotal(subtotal, paymentMethod.getDiscount());

        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setUserId(user.getUserId());
        saleOrder.setName(user.getName());
        saleOrder.setLastname(user.getLastname());
        saleOrder.setEmail(user.getEmail());
        saleOrder.setPaymentMethodName(paymentMethod.getName());
        saleOrder.setPaymentMethodDiscount(paymentMethod.getDiscount());
        saleOrder.setShippingMethodName(shippingMethod.getName());
        saleOrder.setState(SaleOrderState.ENTREGAR_PRODUCTOS);
        saleOrder.setAddresses(toSaleAddress(addresses, saleOrder));
        saleOrder.setProducts(toSaleProduct(products, saleOrder));
        saleOrder.setSubtotal(subtotal);
        saleOrder.setTotal(total);
        saleOrder.setCartId(cart.getCartId());

        return saleOrder;

    }


    private Double calculateSubtotal(List<CartProduct> products) {
        return products.stream().mapToDouble(product -> product.getProduct().getPrice() * product.getQuantity()).sum();
    }

    private Double calculateTotal(Double subtotal, Double discount) {
        return subtotal * ((100 - discount) / 100);
    }

    private Set<SaleAddress> toSaleAddress(Set<TemporaryCartAddress> temporaryCartAddress, SaleOrder saleOrder) {
        return temporaryCartAddress.stream().map(address -> {
            SaleAddress saleAddress = new SaleAddress();
            saleAddress.setStreet(address.getStreet());
            saleAddress.setStreetNumber(address.getStreetNumber());
            saleAddress.setFloor(address.getFloor());
            saleAddress.setApartmentNumber(address.getApartmentNumber());
            saleAddress.setTypeOfAddress(address.getTypeOfAddress());
            saleAddress.setSaleOrder(saleOrder);
            return saleAddress;
        }).collect(Collectors.toSet());
    }

    private List<SaleProduct> toSaleProduct(List<CartProduct> products, SaleOrder saleOrder) {

        return products.stream().map(product -> {

            SaleProduct saleProduct = new SaleProduct();
            Product prod = product.getProduct();

            saleProduct.setProductId(prod.getProductId());
            saleProduct.setName(prod.getName());
            saleProduct.setDescription(prod.getDescription());
            saleProduct.setPrice(prod.getPrice());
            saleProduct.setQuantity(product.getQuantity());
            saleProduct.setAddedToCart(product.getCreated());
            saleProduct.setSaleOrder(saleOrder);

            return saleProduct;

        }).collect(Collectors.toList());
    }
}
