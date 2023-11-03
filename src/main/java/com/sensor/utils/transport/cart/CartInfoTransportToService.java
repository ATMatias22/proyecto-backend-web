package com.sensor.utils.transport.cart;

import com.sensor.entity.Address;
import com.sensor.entity.PaymentMethod;
import com.sensor.entity.ShippingMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartInfoTransportToService {

    private ShippingMethod chosenShippingMethod;

    private List<Address> chosenAddresses;

    private PaymentMethodInfo chosenPaymentMethod;

}
