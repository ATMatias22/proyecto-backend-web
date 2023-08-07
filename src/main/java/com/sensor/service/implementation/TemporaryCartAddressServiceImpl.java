package com.sensor.service.implementation;

import com.sensor.dao.ITemporaryCartAddressDao;
import com.sensor.entity.Cart;
import com.sensor.entity.TemporaryCartAddress;
import com.sensor.service.ITemporaryCartAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemporaryCartAddressServiceImpl implements ITemporaryCartAddressService {

    @Autowired
    private ITemporaryCartAddressDao temporaryCartAddressDao;

    @Override
    public void saveTemporaryCartAddress(TemporaryCartAddress temporaryCartAddress) {
        this.temporaryCartAddressDao.saveTemporaryCartAddress(temporaryCartAddress);
    }

    @Override
    public void deleteTemporaryCartAddressByCart(Cart cart) {
        this.temporaryCartAddressDao.deleteTemporaryCartAddressByCart(cart);
    }
}
