package com.sensor.mapper;


import com.sensor.dto.cart.response.CartInfoResponse;
import com.sensor.dto.cart.response.CartResponse;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
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

}
