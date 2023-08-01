package com.sensor.dao;

import com.sensor.entity.ShippingMethod;

import java.util.List;
import java.util.Optional;

public interface IShippingMethodDao {

    Optional<ShippingMethod> getShippingMethodByName(String name);

    List<ShippingMethod> getAllShippingMethods();
}
