package com.sensor.utils;

import com.sensor.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTransport {

    private Product product;

    private String image;
}
