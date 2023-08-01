package com.sensor.repository;

import com.sensor.entity.Address;
import com.sensor.entity.TypeOfAddress;
import com.sensor.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByStreetAndStreetNumberAndFloorAndApartmentNumberAndTypeOfAddressAndUser(String street, String streetNumber, String floor, String apartmentNumber, TypeOfAddress typeOfAddress, User user);

}
