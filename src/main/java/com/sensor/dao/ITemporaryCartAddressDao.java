package com.sensor.dao;

import com.sensor.entity.Cart;
import com.sensor.entity.TemporaryCartAddress;

public interface ITemporaryCartAddressDao {

    void saveTemporaryCartAddress(TemporaryCartAddress temporaryCartAddress);

    void deleteTemporaryCartAddressByCart(Cart cart);

}
