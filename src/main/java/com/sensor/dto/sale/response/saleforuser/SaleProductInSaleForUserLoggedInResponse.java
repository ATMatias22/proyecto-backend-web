package com.sensor.dto.sale.response.saleforuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleProductInSaleForUserLoggedInResponse {

    private Long productId;

    private String name;

    private Double price;

    private Long quantity;

    private String description;

    private String addedToCart;


}
