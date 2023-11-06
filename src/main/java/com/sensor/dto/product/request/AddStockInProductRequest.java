package com.sensor.dto.product.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStockInProductRequest {

    @Positive(message = "La cantidad debe ser positivo")
    @NotNull(message = "La cantidad no puede ser nulo")
    private int quantity;

}
