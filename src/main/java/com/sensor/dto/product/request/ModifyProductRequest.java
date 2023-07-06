package com.sensor.dto.product.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyProductRequest {

    private String name;

    private String description;

    private Double price;

}
