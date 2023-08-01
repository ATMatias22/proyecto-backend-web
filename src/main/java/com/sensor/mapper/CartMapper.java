package com.sensor.mapper;


import com.sensor.dto.cart.request.CartInfoRequest;
import com.sensor.dto.cart.response.CartInfoResponse;
import com.sensor.dto.cart.response.CartResponse;
import com.sensor.dto.cart.response.AddProductInCartResponse;
import com.sensor.dto.cart.response.RemoveProductInCartResponse;
import com.sensor.entity.CartProduct;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartInfoTransportToService;
import com.sensor.utils.transport.cart.CartTransportToController;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses =
        {
                PaymentMethodMapper.class,
                ShippingMethodMapper.class,
                AddressMapper.class,
                CartProductMapper.class
        })
public interface CartMapper {

    @Mappings({
            @Mapping(source = "cartInfoTransportToController.cart", target = "cart"),
            @Mapping(source = "cartInfoTransportToController.shippingMethods", target = "shippingMethodsAndUserAddresses.shippingMethods"),
            @Mapping(source = "cartInfoTransportToController.userAddresses", target = "shippingMethodsAndUserAddresses.userAddresses"),
            @Mapping(source = "cartInfoTransportToController.paymentMethods", target = "paymentMethods")
    })
    CartInfoResponse cartTransportToControllerToCartInfoResponse(CartInfoTransportToController cartInfoTransportToController);

    @Mappings({
            @Mapping(source = "cartTransportToController.cart.state", target = "state"),
            @Mapping(source = "cartTransportToController.cart.paymentMethod", target = "paymentMethod"),
            @Mapping(source = "cartTransportToController.cart.shippingMethod", target = "shippingMethod"),
            @Mapping(source = "cartTransportToController.products", target = "cartProducts"),
            @Mapping(source = "cartTransportToController.cart.addresses", target = "addresses")
    })
    CartResponse cartToCartResponse(CartTransportToController cartTransportToController);

    @Mappings({
            @Mapping(source = "cartInfoRequest.shippingMethodAndAddress.shippingMethod", target = "chosenShippingMethod"),
            @Mapping(source = "cartInfoRequest.shippingMethodAndAddress.addresses", target = "chosenAddresses"),
            @Mapping(source = "cartInfoRequest.paymentMethod", target = "chosenPaymentMethod"),
    })
    CartInfoTransportToService cartInfoRequestToCartInfoTransportToService(CartInfoRequest cartInfoRequest);

    @Mappings({
            @Mapping(source = "cartProduct.quantity", target = "currentQuantity"),
    })
    AddProductInCartResponse cartProductToAddProductInCartResponse (CartProduct cartProduct);

    @Mappings({
            @Mapping(source = "cartProduct.quantity", target = "currentQuantity"),
    })
    RemoveProductInCartResponse cartProductToRemoveProductInCartResponse (CartProduct cartProduct);

}
