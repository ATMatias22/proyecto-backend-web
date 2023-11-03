package com.sensor.dto.favorite.response;

import com.sensor.dto.product.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteResponse {

    private ProductResponse product;

    private String created;


}
