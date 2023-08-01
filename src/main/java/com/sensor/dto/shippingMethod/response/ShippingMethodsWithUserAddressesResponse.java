package com.sensor.dto.shippingMethod.response;


import com.sensor.dto.address.response.AddressResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingMethodsWithUserAddressesResponse {

    private List<AddressResponse> userAddresses;

    private List<ShippingMethodResponse> shippingMethods;

}
