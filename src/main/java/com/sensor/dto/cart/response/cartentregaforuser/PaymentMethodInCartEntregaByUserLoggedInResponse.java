package com.sensor.dto.cart.response.cartentregaforuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodInCartEntregaByUserLoggedInResponse {

    private String name;

    private Double discount;

}
