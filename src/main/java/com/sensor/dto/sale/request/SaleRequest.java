package com.sensor.dto.sale.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequest {

    private Long quantity;
    private Long productId;

}
