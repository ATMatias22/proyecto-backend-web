package com.sensor.dto.cart.response.cartforuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInCartForUserResponse {


    private Long id;
    private String name;
    private String description;
    private Long price;
    private String imageBase64;


}
