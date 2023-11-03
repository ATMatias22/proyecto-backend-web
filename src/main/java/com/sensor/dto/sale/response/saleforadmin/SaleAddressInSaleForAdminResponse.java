package com.sensor.dto.sale.response.saleforadmin;

import com.sensor.dto.sale.response.saleforuser.TypeOfAddressInSaleForUserLoggedInResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleAddressInSaleForAdminResponse {

    private String street;

    private String streetNumber;

    private String floor;

    private String apartmentNumber;

    private TypeOfAddressInSaleForAdminResponse typeOfAddress;

}
