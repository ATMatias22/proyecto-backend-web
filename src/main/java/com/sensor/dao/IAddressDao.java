package com.sensor.dao;

import com.sensor.entity.Address;
import com.sensor.entity.TypeOfAddress;
import com.sensor.security.entity.User;

import java.util.Optional;

public interface IAddressDao {

    Optional<Address> getAddressByStreetAndStreetNumberAndFloorAndApartmentNumberAndTypeOfAddressAndUser(String street, String streetNumber, String floor, String apartmentNumber, TypeOfAddress typeOfAddress, User user);

    Address saveAddress(Address address);

}
