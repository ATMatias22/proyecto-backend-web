package com.sensor.dto.shippingMethod.request;

import com.sensor.dto.address.request.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingMethodAndAddressRequest {

    private ShippingMethodRequest shippingMethod;

    private List<AddressRequest> addresses;
}
