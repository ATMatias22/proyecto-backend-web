package com.sensor.dto.cart.response;


import com.sensor.dto.address.response.AddressResponse;
import com.sensor.dto.cartProduct.response.CartProductResponse;
import com.sensor.dto.paymentMethod.response.PaymentMethodResponse;
import com.sensor.dto.shippingMethod.response.ShippingMethodResponse;
import com.sensor.enums.CartState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {


    private CartState state;

    private PaymentMethodResponse paymentMethod;

    private ShippingMethodResponse shippingMethod;

    private List<CartProductResponse> cartProducts;

    private Set<AddressResponse> addresses;

}
