package com.sensor.service;

import com.sensor.entity.Address;
import com.sensor.entity.TypeOfAddress;

public interface IAddressService {


    Address getAddressByStreetAndStreetNumberAndFloorAndApartmentNumberAndTypeOfAddressAndUser(String street, String streetNumber, String floor, String aparmentNumber, TypeOfAddress typeOfAddress);

    Address saveAddress(Address address);

}
