package com.sensor.dao;


import com.sensor.entity.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface IPaymentMethodDao {

    Optional<PaymentMethod> getPaymentMethodByName(String name);

    List<PaymentMethod> getAllPaymentMethods();

}
