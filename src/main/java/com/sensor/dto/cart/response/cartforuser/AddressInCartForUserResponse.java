package com.sensor.dto.cart.response.cartforuser;

import com.sensor.dto.typeOfAddress.response.TypeOfAddressResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressInCartForUserResponse {


    private String street;

    private String streetNumber;

    private String floor;

    private String apartmentNumber;

    private TypeOfAddressInCartForUserResponse typeOfAddress;

}
