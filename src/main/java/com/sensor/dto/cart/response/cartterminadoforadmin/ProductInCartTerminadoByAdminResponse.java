package com.sensor.dto.cart.response.cartterminadoforadmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInCartTerminadoByAdminResponse {

    private Long productId;

    private String name;

    private Double price;

}
