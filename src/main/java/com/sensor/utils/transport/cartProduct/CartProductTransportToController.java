package com.sensor.utils.transport.cartProduct;

import com.sensor.utils.transport.product.ProductTransportToController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductTransportToController {

    private ProductTransportToController product;

    private Double quantity;

    private LocalDateTime dateTimeAdded;
}
