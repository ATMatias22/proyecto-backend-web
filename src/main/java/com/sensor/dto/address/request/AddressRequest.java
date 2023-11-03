package com.sensor.dto.address.request;

import com.sensor.dto.typeOfAddress.request.TypeOfAddressRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    private String street;

    private String streetNumber;

    private String floor;

    private String apartmentNumber;

    private TypeOfAddressRequest typeOfAddress;

}
