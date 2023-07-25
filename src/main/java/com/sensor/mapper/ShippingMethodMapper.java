package com.sensor.mapper;

import com.sensor.dto.shippingMethod.response.ShippingMethodResponse;
import com.sensor.entity.ShippingMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShippingMethodMapper {

    @Mappings({
            @Mapping(source = "shippingMethod.name", target = "name")

    })
    ShippingMethodResponse toShippingMethodResponse(ShippingMethod shippingMethod);
    List<ShippingMethodResponse> toShippingMethodResponse(List<ShippingMethod> shippingMethod);

}
