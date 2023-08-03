package com.sensor.dto.cart.response.cartforuser;

import com.sensor.dto.product.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductInCartForUserResponse {


    private ProductInCartForUserResponse product;

    private Double quantity;

    private String dateTimeAdded;


}
