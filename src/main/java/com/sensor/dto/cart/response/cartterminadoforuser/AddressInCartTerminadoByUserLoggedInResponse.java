package com.sensor.dto.cart.response.cartterminadoforuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressInCartTerminadoByUserLoggedInResponse {

    private String street;

    private String streetNumber;

    private String floor;

    private String apartmentNumber;

    private TypeOfAddressInCartTerminadoByUserLoggedInResponse typeOfAddress;

}
