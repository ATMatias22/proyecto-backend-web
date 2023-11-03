package com.sensor.dto.cartProduct.response;


import com.sensor.dto.product.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductResponse {

    private ProductResponse product;

    private Double quantity;

    private String dateTimeAdded;

}
