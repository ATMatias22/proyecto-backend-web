package com.sensor.dto.cart.response;


import com.sensor.dto.paymentMethod.response.PaymentMethodResponse;
import com.sensor.dto.shippingMethod.response.ShippingMethodsWithUserAddressesResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartInfoResponse {

    private CartResponse cart;

    private ShippingMethodsWithUserAddressesResponse shippingMethodsAndUserAddresses;

    private List<PaymentMethodResponse> paymentMethods;


}
