package com.sensor.pattern.cart.strategy;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.sensor.dao.ICartDao;
import com.sensor.entity.*;
import com.sensor.enums.CartState;
import com.sensor.enums.SaleOrderState;
import com.sensor.exception.GeneralException;
import com.sensor.external.dto.CardPaymentDTO;
import com.sensor.external.exception.MercadoPagoException;
import com.sensor.security.entity.User;
import com.sensor.service.*;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartInfoTransportToService;
import com.sensor.utils.transport.cart.CartTransportToController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Value("${MP}")
    private String accessToken; // Coloca tu token de acceso aquí



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
        return CartState.TERMINADO;
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
        PaymentMethod paymentMethod = this.paymentMethodService.getPaymentMethodByName("Tarjeta");


        BigDecimal subtotalCart = BigDecimal.valueOf(this.calculateSubtotal(cart.getCartProducts()));

        BigDecimal amountSent = cartDataRequest.getChosenPaymentMethod().getPaymentInformation().getTransactionAmount();
        System.out.println(subtotalCart);

        if(subtotalCart.compareTo(amountSent) != 0){
            throw new GeneralException(HttpStatus.BAD_REQUEST, "El monto enviado no es el mismo que el total del carrito");
        }

        //seteamos el metodo de pago elegido al carrito. para que cuando se cree la orden detecte que descuento tiene ese metodo de pago
        cart.setPaymentMethod(paymentMethod);

        CardPaymentDTO cardPaymentDTO = cartDataRequest.getChosenPaymentMethod().getPaymentInformation();

        cardPaymentDTO.setTransactionAmount(BigDecimal.valueOf(this.calculateTotal(subtotalCart.doubleValue(), paymentMethod.getDiscount())));

        //creamos el pago
        this.makeCardPayment(cardPaymentDTO);

        //le seteamos al carrito que ira al controller y que tiene las imagenes en base 64 los mismos datos de direcciones, metodo de envio y metodode de pago.
        cartTransport.getCart().setPaymentMethod(paymentMethod);
        cartTransport.getCart().setState(this.getNextState());

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


    public void makeCardPayment(CardPaymentDTO cardPaymentDTO){

        try {
            MercadoPagoConfig.setAccessToken(accessToken);

            PaymentClient paymentClient = new PaymentClient();
            System.out.println(cardPaymentDTO.getProductDescription());

            PaymentCreateRequest paymentCreateRequest =
                    PaymentCreateRequest.builder()
                            .transactionAmount(cardPaymentDTO.getTransactionAmount())
                            .token(cardPaymentDTO.getToken())
                            .description(cardPaymentDTO.getProductDescription())
                            .installments(cardPaymentDTO.getInstallments())
                            .paymentMethodId(cardPaymentDTO.getPaymentMethodId())
                            .payer(
                                    PaymentPayerRequest.builder()
                                            .email(cardPaymentDTO.getPayer().getEmail())
                                            .identification(
                                                    IdentificationRequest.builder()
                                                            .type(cardPaymentDTO.getPayer().getIdentification().getType())
                                                            .number(cardPaymentDTO.getPayer().getIdentification().getNumber())
                                                            .build())
                                            .build())
                            .build();


            Payment createdPayment = paymentClient.create(paymentCreateRequest);


        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Problemas para procesar el pago");
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR,"Problemas para procesar el pago");
        }

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
