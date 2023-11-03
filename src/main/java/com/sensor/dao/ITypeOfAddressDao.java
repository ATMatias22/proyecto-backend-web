package com.sensor.dao;

import com.sensor.entity.TypeOfAddress;

import java.util.Optional;

public interface ITypeOfAddressDao {

    Optional<TypeOfAddress> getTypeOfAddressByName(String name);
}
