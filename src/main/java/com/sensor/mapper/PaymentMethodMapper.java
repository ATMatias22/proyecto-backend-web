package com.sensor.mapper;

import com.sensor.dto.paymentMethod.request.PaymentMethodRequest;
import com.sensor.dto.paymentMethod.response.PaymentMethodResponse;
import com.sensor.entity.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

    @Mappings({
            @Mapping(source = "paymentMethod.name", target = "name"),
            @Mapping(source = "paymentMethod.discount", target = "discount")

    })
    PaymentMethodResponse toPaymentMethodResponse(PaymentMethod paymentMethod);

    List<PaymentMethodResponse> toPaymentMethodResponse(List<PaymentMethod> paymentMethod);


    @Mappings({
            @Mapping(source = "paymentMethodRequest.name", target = "name"),
            @Mapping(target = "discount", ignore = true),
            @Mapping(target = "paymentMethodId", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "updated", ignore = true)

    })
    PaymentMethod paymentMethodRequestToPaymentMethod(PaymentMethodRequest paymentMethodRequest);



}
