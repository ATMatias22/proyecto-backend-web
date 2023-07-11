package com.sensor.dto.purchasedHardware.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedHardwareRequest {

    @NotBlank(message = "El nombre no puede ser nulo ni vacio")
    private String name;

    @NotNull(message = "La cantidad no puede ser nulo")
    @Positive(message = "La cantidad debe ser positivo")
    private Integer quantity;

    @NotBlank(message = "La fecha de la compra no puede ser nulo ni vacio")
    private String datePurchase;

    @NotBlank(message = "El proveedor no puede ser nulo ni vacio")
    private String provider;

    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser positivo")
    private Long price;

}
