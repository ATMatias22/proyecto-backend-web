package com.sensor.mapper;


import com.sensor.dto.cart.request.CartInfoRequest;
import com.sensor.dto.cart.response.*;
import com.sensor.dto.cart.response.cartforuser.*;
import com.sensor.entity.*;
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







}
