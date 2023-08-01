package com.sensor.dto.cart.request;

import com.sensor.dto.paymentMethod.request.PaymentMethodRequest;
import com.sensor.dto.shippingMethod.request.ShippingMethodAndAddressRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartInfoRequest {

    private ShippingMethodAndAddressRequest shippingMethodAndAddress;

    private PaymentMethodRequest paymentMethod;

}
