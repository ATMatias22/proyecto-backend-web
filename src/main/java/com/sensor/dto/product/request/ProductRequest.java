package com.sensor.dto.product.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {


    @NotBlank(message = "El nombre no puede ser nulo ni vacio")
    private String name;

    @NotBlank(message = "La descripcion no puede ser nulo ni vacio")
    private String description;

    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser positivo")
    private Double price;

}
