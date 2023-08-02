package com.sensor.dto.cart.response.cartentregaforuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressInCartEntregaByUserLoggedInResponse {

    private String street;

    private String streetNumber;

    private String floor;

    private String apartmentNumber;

    private TypeOfAddressInCartEntregaByUserLoggedInResponse typeOfAddress;

}
