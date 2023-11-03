package com.sensor.dao.implementation;


import com.sensor.dao.IAddressDao;
import com.sensor.entity.Address;
import com.sensor.entity.TypeOfAddress;
import com.sensor.repository.IAddressRepository;
import com.sensor.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressDaoImpl implements IAddressDao {

    @Autowired
    private IAddressRepository addressRepository;

    @Override
    public Optional<Address> getAddressByStreetAndStreetNumberAndFloorAndApartmentNumberAndTypeOfAddressAndUser(String street, String streetNumber, String floor, String apartmentNumber, TypeOfAddress typeOfAddress, User user) {
        return this.addressRepository.findByStreetAndStreetNumberAndFloorAndApartmentNumberAndTypeOfAddressAndUser(street,streetNumber,floor,apartmentNumber,typeOfAddress,user);
    }

    @Override
    public Address saveAddress(Address address) {
        return this.addressRepository.save(address);
    }
}
