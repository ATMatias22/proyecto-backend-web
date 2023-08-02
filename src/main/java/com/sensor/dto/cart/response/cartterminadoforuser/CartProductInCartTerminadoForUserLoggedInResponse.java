package com.sensor.dto.cart.response.cartterminadoforuser;

import com.sensor.dto.cart.response.cartterminadoforadmin.ProductInCartTerminadoByAdminResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductInCartTerminadoForUserLoggedInResponse {

    ProductInCartTerminadoByUserLoggedInResponse product;

    private Long quantity;
}
