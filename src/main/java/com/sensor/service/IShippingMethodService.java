package com.sensor.service;


import com.sensor.entity.ShippingMethod;

import java.util.List;

public interface IShippingMethodService {

    ShippingMethod getShippingMethodByName(String name);

    List<ShippingMethod> getAllShippingMethods();
}
