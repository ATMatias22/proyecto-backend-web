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
public class CartInfoTransportToController {


    private CartTransportToController cart;

    private List<ShippingMethod> shippingMethods;

    private List<PaymentMethod> paymentMethods;

    private List<Address> userAddresses;



}
