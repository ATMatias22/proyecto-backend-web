package com.sensor.service.implementation;

import com.sensor.dao.IAddressDao;
import com.sensor.entity.Address;
import com.sensor.entity.TypeOfAddress;
import com.sensor.exception.GeneralException;
import com.sensor.security.MainUser;
import com.sensor.security.entity.User;
import com.sensor.security.service.IUserService;
import com.sensor.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private IAddressDao addressDao;

    @Autowired
    private IUserService userService;

    @Override
    public Address getAddressByStreetAndStreetNumberAndFloorAndApartmentNumberAndTypeOfAddressAndUser(String street, String streetNumber, String floor, String aparmentNumber, TypeOfAddress typeOfAddress) {

        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());

        return this.addressDao.getAddressByStreetAndStreetNumberAndFloorAndApartmentNumberAndTypeOfAddressAndUser(street,streetNumber,floor,aparmentNumber,typeOfAddress,userLoggedIn).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontró esta direccion para este usuario"));
    }

    @Override
    public Address saveAddress(Address address) {
        return this.addressDao.saveAddress(address);
    }
}
