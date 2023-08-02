package com.sensor.dto.cart.response.cartterminadoforadmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinishedCartTerminadoForAdminResponse {

    private Long cartId;
    private String datePurchase;

    private UserInCartTerminadoByAdminResponse user;

    private List<CartProductInCartTerminadoForAdminResponse> cartProducts;

}
