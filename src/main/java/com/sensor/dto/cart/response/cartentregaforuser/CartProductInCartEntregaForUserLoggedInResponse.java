package com.sensor.dto.cart.response.cartentregaforuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductInCartEntregaForUserLoggedInResponse {

    ProductInCartEntregaByUserLoggedInResponse product;

    private Long quantity;
}
