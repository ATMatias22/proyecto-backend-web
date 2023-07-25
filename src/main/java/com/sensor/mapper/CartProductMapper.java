package com.sensor.mapper;

import com.sensor.dto.cartProduct.response.CartProductResponse;
import com.sensor.entity.CartProduct;
import com.sensor.utils.date.StringToLocalDateTimeAndViceVersa;
import com.sensor.utils.transport.cartProduct.CartProductTransportToController;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ProductMapper.class
})
public abstract class CartProductMapper {

    @Autowired
    protected StringToLocalDateTimeAndViceVersa stdv;


    @Mappings({
            @Mapping(source = "cartProductTransportToController.product", target = "product"),
            @Mapping(source = "cartProductTransportToController.quantity", target = "quantity"),
            @Mapping(target = "cartProductTransportToController.dateTimeAdded", expression = "java(stdv.getString(cartProductTransportToController.getDateTimeAdded()))")
    })
    public abstract CartProductResponse cartProductTransportToControllerToCartProductResponse(CartProductTransportToController cartProductTransportToController);
    public abstract List<CartProductResponse> cartProductTransportToControllerToCartProductResponse(List<CartProductTransportToController> cartProductTransportToController);



}
