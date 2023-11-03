package com.sensor.utils.transport.favorite;


import com.sensor.entity.Favorite;
import com.sensor.utils.transport.product.ProductTransportToController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteTransportToController {

    private ProductTransportToController product;

    private LocalDateTime created;

}
