package com.sensor.dto.cart.response.cartforuser;


import com.sensor.dto.cart.response.cartforuser.extradata.ShippingMethodsWithUserAddressesInCartForUserResponse;
import com.sensor.dto.paymentMethod.response.PaymentMethodResponse;
import com.sensor.dto.shippingMethod.response.ShippingMethodsWithUserAddressesResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartInfoForUserResponse {

    private CartForUserResponse cart;

    private ShippingMethodsWithUserAddressesInCartForUserResponse shippingMethodsAndUserAddresses;

    private List<PaymentMethodInCartForUserResponse> paymentMethods;


}
