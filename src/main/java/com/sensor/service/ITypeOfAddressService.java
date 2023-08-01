package com.sensor.service;

import com.sensor.entity.TypeOfAddress;

public interface ITypeOfAddressService {

    TypeOfAddress getTypeOfAddressByName(String name);

}
