package com.sensor.dto.cart.response.cartentregaforuser;

import com.sensor.enums.CartState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartEntregaForUserLoggedInResponse {

    private CartState state;

    private PaymentMethodInCartEntregaByUserLoggedInResponse paymentMethod;

    private ShippingMethodInCartEntregaByUserLoggedInResponse shippingMethod;

    private List<CartProductInCartEntregaForUserLoggedInResponse> cartProducts;

    private Set<AddressInCartEntregaByUserLoggedInResponse> addresses;

}
