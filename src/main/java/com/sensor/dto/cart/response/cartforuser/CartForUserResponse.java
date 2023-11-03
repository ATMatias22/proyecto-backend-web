package com.sensor.dto.cart.response.cartforuser;


import com.sensor.dto.address.response.AddressResponse;
import com.sensor.dto.cartProduct.response.CartProductResponse;
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
public class CartForUserResponse {


    private CartState state;

    private PaymentMethodInCartForUserResponse paymentMethod;

    private ShippingMethodInCartForUserResponse shippingMethod;

    private List<CartProductInCartForUserResponse> cartProducts;

    private List<AddressInCartForUserResponse> addresses;

}
