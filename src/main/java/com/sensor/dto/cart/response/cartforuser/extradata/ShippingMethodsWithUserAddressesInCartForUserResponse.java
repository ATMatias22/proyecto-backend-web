package com.sensor.dto.cart.response.cartforuser.extradata;

import com.sensor.dto.address.response.AddressResponse;
import com.sensor.dto.cart.response.cartforuser.AddressInCartForUserResponse;
import com.sensor.dto.cart.response.cartforuser.ShippingMethodInCartForUserResponse;
import com.sensor.dto.cart.response.cartforuser.TypeOfAddressInCartForUserResponse;
import com.sensor.dto.shippingMethod.response.ShippingMethodResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingMethodsWithUserAddressesInCartForUserResponse {

    private List<AddressInCartForUserResponse> userAddresses;

    private List<ShippingMethodInCartForUserResponse> shippingMethods;

}
