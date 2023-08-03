package com.sensor.dto.cart.response.cartterminadoforuser;

import com.sensor.enums.CartState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartTerminadoForUserLoggedInResponse {

    private CartState state;

    private PaymentMethodInCartTerminadoByUserLoggedInResponse paymentMethod;

    private ShippingMethodInCartTerminadoByUserLoggedInResponse shippingMethod;

    private List<CartProductInCartTerminadoForUserLoggedInResponse> cartProducts;

    private List<AddressInCartTerminadoByUserLoggedInResponse> addresses;

}
