package com.sensor.dto.cart.response.cartforuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodInCartForUserResponse {


    private String name;

    private Double discount;

}
