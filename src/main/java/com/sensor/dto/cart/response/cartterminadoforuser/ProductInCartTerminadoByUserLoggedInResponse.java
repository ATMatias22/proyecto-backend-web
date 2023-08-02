package com.sensor.dto.cart.response.cartterminadoforuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInCartTerminadoByUserLoggedInResponse {

    private Long productId;

    private String name;

    private Double price;

}
