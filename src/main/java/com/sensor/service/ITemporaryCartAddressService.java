package com.sensor.service;

import com.sensor.entity.Cart;
import com.sensor.entity.TemporaryCartAddress;

public interface ITemporaryCartAddressService {


    void saveTemporaryCartAddress(TemporaryCartAddress temporaryCartAddress);

    void deleteTemporaryCartAddressByCart(Cart cart);

}
