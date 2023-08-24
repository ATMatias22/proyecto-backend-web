package com.sensor.mapper;

import com.sensor.dto.paymentMethod.request.PaymentMethodRequest;
import com.sensor.dto.paymentMethod.response.PaymentMethodResponse;
import com.sensor.entity.PaymentMethod;
import com.sensor.utils.transport.cart.PaymentMethodInfo;
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
            @Mapping(source = "paymentMethodRequest.paymentMethod.name", target = "name"),
            @Mapping(source = "paymentMethodRequest.informationCard", target = "paymentInformation"),


    })
    PaymentMethodInfo paymentMethodRequestToPaymentMethod(PaymentMethodRequest paymentMethodRequest);



}
