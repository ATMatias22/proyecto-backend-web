package com.sensor.service;

import com.sensor.entity.PaymentMethod;

import java.util.List;

public interface IPaymentMethodService {

    PaymentMethod getPaymentMethodByName(String name);

    List<PaymentMethod> getAllPaymentMethods();

}
