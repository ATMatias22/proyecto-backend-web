package com.sensor.dto.sale.response.saleforuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleAddressInSaleForUserLoggedInResponse {

    private String street;

    private String streetNumber;

    private String floor;

    private String apartmentNumber;

    private TypeOfAddressInSaleForUserLoggedInResponse typeOfAddress;

}
