package com.sensor.mapper;


import com.sensor.dto.cart.request.CartInfoRequest;
import com.sensor.dto.cart.response.*;
import com.sensor.dto.cart.response.cartentregaforuser.*;
import com.sensor.dto.cart.response.cartforuser.*;
import com.sensor.dto.cart.response.cartterminadoforadmin.CartProductInCartTerminadoForAdminResponse;
import com.sensor.dto.cart.response.cartterminadoforadmin.FinishedCartTerminadoForAdminResponse;
import com.sensor.dto.cart.response.cartterminadoforadmin.ProductInCartTerminadoByAdminResponse;
import com.sensor.dto.cart.response.cartterminadoforadmin.UserInCartTerminadoByAdminResponse;
import com.sensor.dto.cart.response.cartterminadoforuser.*;
import com.sensor.entity.*;
import com.sensor.security.entity.User;
import com.sensor.utils.date.StringToLocalDateTimeAndViceVersa;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartInfoTransportToService;
import com.sensor.utils.transport.cart.CartTransportToController;
import com.sensor.utils.transport.product.ProductTransportToController;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses =
        {
                PaymentMethodMapper.class,
                ShippingMethodMapper.class,
                AddressMapper.class,
                CartProductMapper.class
        })
public abstract class CartMapper {

    @Autowired
    protected StringToLocalDateTimeAndViceVersa stdv;


    @Mappings({
            @Mapping(source = "cartInfoRequest.shippingMethodAndAddress.shippingMethod", target = "chosenShippingMethod"),
            @Mapping(source = "cartInfoRequest.shippingMethodAndAddress.addresses", target = "chosenAddresses"),
            @Mapping(source = "cartInfoRequest.paymentMethod", target = "chosenPaymentMethod"),
    })
    public abstract CartInfoTransportToService cartInfoRequestToCartInfoTransportToService(CartInfoRequest cartInfoRequest);


    @Mappings({
            @Mapping(source = "cartProduct.quantity", target = "currentQuantity"),
    })
    public abstract AddProductInCartResponse cartProductToAddProductInCartResponse(CartProduct cartProduct);


    @Mappings({
            @Mapping(source = "cartProduct.quantity", target = "currentQuantity"),
    })
    public abstract RemoveProductInCartResponse cartProductToRemoveProductInCartResponse(CartProduct cartProduct);



    //------------------------------------------------------------------------------------------------------------------------------------------
    //Carrito para usuario y para mostrar los estados del carrito
    @Mappings({
            @Mapping(source = "cartInfoTransportToController.cart", target = "cart"),
            @Mapping(source = "cartInfoTransportToController.shippingMethods", target = "shippingMethodsAndUserAddresses.shippingMethods"),
            @Mapping(source = "cartInfoTransportToController.userAddresses", target = "shippingMethodsAndUserAddresses.userAddresses"),
            @Mapping(source = "cartInfoTransportToController.paymentMethods", target = "paymentMethods")
    })
    public abstract CartInfoForUserResponse cartTransportToControllerToCartInfoResponse(CartInfoTransportToController cartInfoTransportToController);

    @Mappings({
            @Mapping(source = "cartTransportToController.cart.state", target = "state"),
            @Mapping(source = "cartTransportToController.cart.paymentMethod", target = "paymentMethod"),
            @Mapping(source = "cartTransportToController.cart.shippingMethod", target = "shippingMethod"),
            @Mapping(source = "cartTransportToController.products", target = "cartProducts"),
            @Mapping(source = "cartTransportToController.cart.temporaryCartAddresses", target = "addresses")
    })
    public abstract CartForUserResponse cartTransportToControllerToCartResponse(CartTransportToController cartTransportToController);


    @Mappings({
            @Mapping(source = "cartProduct.product", target = "product"),
            @Mapping(source = "cartProduct.quantity", target = "quantity"),
            @Mapping(target = "dateTimeAdded", expression = "java(stdv.getString(cartProduct.getCreated()))"),
    })
    public abstract CartProductInCartForUserResponse cartToCartProductInCartForUserResponse(CartProduct cartProduct);
    public abstract List<CartProductInCartForUserResponse> cartToCartProductInCartForUserResponse(List<CartProduct> cartProduct);

    @Mappings({
            @Mapping(source = "productTransportToController.product.productId", target = "id"),
            @Mapping(source = "productTransportToController.product.name", target = "name"),
            @Mapping(source = "productTransportToController.product.description", target = "description"),
            @Mapping(source = "productTransportToController.product.price", target = "price"),
            @Mapping(source = "productTransportToController.fileInBase64", target = "imageBase64"),
    })
    public abstract ProductInCartForUserResponse productTransportToControllerToProductInCartForUserResponse(ProductTransportToController productTransportToController);

    @Mappings({
            @Mapping(source = "address.street", target = "street"),
            @Mapping(source = "address.streetNumber", target = "streetNumber"),
            @Mapping(source = "address.floor", target = "floor"),
            @Mapping(source = "address.apartmentNumber", target = "apartmentNumber"),
            @Mapping(source = "address.typeOfAddress", target = "typeOfAddress"),
    })
    public abstract AddressInCartForUserResponse cartAddressToAddressInCartForUserResponse(TemporaryCartAddress address);
    public abstract List<AddressInCartForUserResponse> cartAddressToAddressInCartForUserResponse(List<TemporaryCartAddress> address);

    @Mappings({
            @Mapping(source = "typeOfAddress", target = "name"),
    })
    public abstract TypeOfAddressInCartForUserResponse typeOfAddressToTypeOfAddressInCartForUserResponse(String typeOfAddress);



    @Mappings({
            @Mapping(source = "paymentMethod.name", target = "name"),
            @Mapping(source = "paymentMethod.discount", target = "discount"),
    })
    public abstract PaymentMethodInCartForUserResponse paymentMethodToPaymentMethodInCartForUserResponse(PaymentMethod paymentMethod);

    @Mappings({
            @Mapping(source = "shippingMethod.name", target = "name"),
    })
    public abstract ShippingMethodInCartForUserResponse shippingMethodToShippingMethodInCartForUserResponse(ShippingMethod shippingMethod);
    public abstract List<ShippingMethodInCartForUserResponse> shippingMethodToShippingMethodInCartForUserResponse(List<ShippingMethod> shippingMethod);




    //------------------------------------------------------------------------------------------------------------------------------------------
    //todos los carritos en estado terminado para que el usuario administrador pueda ver las ventas.
    @Mappings({
            @Mapping(source = "cart.cartId", target = "cartId"),
            @Mapping(target = "datePurchase", expression = "java(stdv.getString(cart.getUpdated()))"),
            @Mapping(source = "cart.user", target = "user"),
            @Mapping(source = "cart.cartProducts", target = "cartProducts"),
    })
    public abstract FinishedCartTerminadoForAdminResponse cartToFinishedCartTerminadoForAdminResponse(Cart cart);

    public abstract List<FinishedCartTerminadoForAdminResponse> cartToFinishedCartTerminadoForAdminResponse(List<Cart> cart);

    @Mappings({
            @Mapping(source = "user.userId", target = "userId"),
            @Mapping(source = "user.name", target = "name"),
            @Mapping(source = "user.lastname", target = "lastname"),
            @Mapping(source = "user.email", target = "email"),
    })
    public abstract UserInCartTerminadoByAdminResponse userToUserInCartTerminadoByAdminResponse(User user);


    @Mappings({
            @Mapping(source = "cartProduct.product", target = "product"),
            @Mapping(source = "cartProduct.quantity", target = "quantity"),
    })
    public abstract CartProductInCartTerminadoForAdminResponse cartToCartProductInCartTerminadoForAdminResponse(CartProduct cartProduct);

    public abstract List<CartProductInCartTerminadoForAdminResponse> cartToCartProductInCartTerminadoForAdminResponse(List<CartProduct> cartProduct);

    @Mappings({
            @Mapping(source = "product.name", target = "name"),
            @Mapping(source = "product.price", target = "price"),
            @Mapping(source = "product.productId", target = "productId"),
    })
    public abstract ProductInCartTerminadoByAdminResponse productToProductInCartTerminadoByAdminResponse(Product product);

    //------------------------------------------------------------------------------------------------------------------------------------------
    //Carrito en estado entrega para usuarios logueados.

    @Mappings({
            @Mapping(source = "cart.state", target = "state"),
            @Mapping(source = "cart.paymentMethod", target = "paymentMethod"),
            @Mapping(source = "cart.shippingMethod", target = "shippingMethod"),
            @Mapping(source = "cart.cartProducts", target = "cartProducts"),
            @Mapping(source = "cart.temporaryCartAddresses", target = "addresses"),
    })
    public abstract CartEntregaForUserLoggedInResponse cartToCartEntregaForUserLoggedInResponse(Cart cart);
    public abstract List<CartEntregaForUserLoggedInResponse> cartToCartEntregaForUserLoggedInResponse(List<Cart> cart);

    @Mappings({
            @Mapping(source = "address.street", target = "street"),
            @Mapping(source = "address.streetNumber", target = "streetNumber"),
            @Mapping(source = "address.floor", target = "floor"),
            @Mapping(source = "address.apartmentNumber", target = "apartmentNumber"),
            @Mapping(source = "address.typeOfAddress", target = "typeOfAddress"),
    })
    public abstract AddressInCartEntregaByUserLoggedInResponse cartAddressToAddressInCartEntregaByUserLoggedInResponse(TemporaryCartAddress address);
    public abstract List<AddressInCartEntregaByUserLoggedInResponse> cartAddressToAddressInCartEntregaByUserLoggedInResponse(List<TemporaryCartAddress> address);

    @Mappings({
            @Mapping(source = "typeOfAddress", target = "name"),
    })
    public abstract TypeOfAddressInCartEntregaByUserLoggedInResponse typeOfAddressToTypeOfAddressInCartEntregaByUserLoggedInResponse(String typeOfAddress);


    @Mappings({
            @Mapping(source = "cartProduct.product", target = "product"),
            @Mapping(source = "cartProduct.quantity", target = "quantity"),
    })
    public abstract CartProductInCartEntregaForUserLoggedInResponse cartProductToCartProductInCartEntregaForUserLoggedInResponse(CartProduct cartProduct);
    public abstract List<CartProductInCartEntregaForUserLoggedInResponse> cartProductToCartProductInCartEntregaForUserLoggedInResponse(List<CartProduct> cartProduct);


    @Mappings({
            @Mapping(source = "product.productId", target = "productId"),
            @Mapping(source = "product.name", target = "name"),
            @Mapping(source = "product.price", target = "price"),
    })
    public abstract ProductInCartEntregaByUserLoggedInResponse productToProductInCartEntregaByUserLoggedInResponse(Product product);

    @Mappings({
            @Mapping(source = "shippingMethod.name", target = "name"),
    })
    public abstract ShippingMethodInCartEntregaByUserLoggedInResponse shippingMethodToShippingMethodInCartEntregaByUserLoggedInResponse(ShippingMethod shippingMethod);

    @Mappings({
            @Mapping(source = "paymentMethod.name", target = "name"),
            @Mapping(source = "paymentMethod.discount", target = "discount"),
    })
    public abstract PaymentMethodInCartEntregaByUserLoggedInResponse paymentMethodToPaymentMethodInCartEntregaByUserLoggedInResponse(PaymentMethod paymentMethod);

    //------------------------------------------------------------------------------------------------------------------------------------------
    //Carrito en estado terminado para usuarios logueados.

    @Mappings({
            @Mapping(source = "cart.state", target = "state"),
            @Mapping(source = "cart.paymentMethod", target = "paymentMethod"),
            @Mapping(source = "cart.shippingMethod", target = "shippingMethod"),
            @Mapping(source = "cart.cartProducts", target = "cartProducts"),
            @Mapping(source = "cart.temporaryCartAddresses", target = "addresses"),
    })
    public abstract CartTerminadoForUserLoggedInResponse cartToCartTerminadoForUserLoggedInResponse(Cart cart);
    public abstract List<CartTerminadoForUserLoggedInResponse> cartToCartTerminadoForUserLoggedInResponse(List<Cart> cart);

    @Mappings({
            @Mapping(source = "address.street", target = "street"),
            @Mapping(source = "address.streetNumber", target = "streetNumber"),
            @Mapping(source = "address.floor", target = "floor"),
            @Mapping(source = "address.apartmentNumber", target = "apartmentNumber"),
            @Mapping(source = "address.typeOfAddress", target = "typeOfAddress"),
    })
    public abstract AddressInCartTerminadoByUserLoggedInResponse addressToAddressInCartTerminadoByUserLoggedInResponse(Address address);
    public abstract List<AddressInCartTerminadoByUserLoggedInResponse> addressToAddressInCartTerminadoByUserLoggedInResponse(List<Address> address);

    @Mappings({
            @Mapping(source = "typeOfAddress", target = "name"),
    })
    public abstract TypeOfAddressInCartTerminadoByUserLoggedInResponse typeOfAddressToTypeOfAddressInCartTerminadoByUserLoggedInResponse(String typeOfAddress);


    @Mappings({
            @Mapping(source = "cartProduct.product", target = "product"),
            @Mapping(source = "cartProduct.quantity", target = "quantity"),
    })
    public abstract CartProductInCartTerminadoForUserLoggedInResponse cartProductToCartProductInCartTerminadoForUserLoggedInResponse(CartProduct cartProduct);
    public abstract List<CartProductInCartTerminadoForUserLoggedInResponse> cartProductToCartProductInCartTerminadoForUserLoggedInResponse(List<CartProduct> cartProduct);


    @Mappings({
            @Mapping(source = "product.productId", target = "productId"),
            @Mapping(source = "product.name", target = "name"),
            @Mapping(source = "product.price", target = "price"),
    })
    public abstract ProductInCartTerminadoByUserLoggedInResponse productToProductInCartTerminadoByUserLoggedInResponse(Product product);

    @Mappings({
            @Mapping(source = "shippingMethod.name", target = "name"),
    })
    public abstract ShippingMethodInCartTerminadoByUserLoggedInResponse shippingMethodToShippingMethodInCartTerminadoByUserLoggedInResponse(ShippingMethod shippingMethod);

    @Mappings({
            @Mapping(source = "paymentMethod.name", target = "name"),
            @Mapping(source = "paymentMethod.discount", target = "discount"),
    })
    public abstract PaymentMethodInCartTerminadoByUserLoggedInResponse paymentMethodToPaymentMethodInCartTerminadoByUserLoggedInResponse(PaymentMethod paymentMethod);

    //------------------------------------------------------------------------------------------------------------------------------------------


}