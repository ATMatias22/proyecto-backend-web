package com.sensor.dto.address.response;


import com.sensor.dto.typeOfAddress.response.TypeOfAddressResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {


    private String street;

    private String streetNumber;

    private String floor;

    private String apartmentNumber;

    private TypeOfAddressResponse typeOfAddress;

}
