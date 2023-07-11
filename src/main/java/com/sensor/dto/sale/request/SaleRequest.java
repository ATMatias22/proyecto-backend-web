package com.sensor.dto.sale.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequest {

    @NotNull(message = "La cantidad no puede ser nulo")
    @Positive(message = "La cantidad debe ser positivo")
    private Long quantity;

    @NotNull(message = "El id del producto no puede ser nulo")
    private Long productId;

}
