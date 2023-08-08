package com.sensor.dto.cart.response.cartterminadoforuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodInCartTerminadoByUserLoggedInResponse {

    private String name;

    private Double discount;

}