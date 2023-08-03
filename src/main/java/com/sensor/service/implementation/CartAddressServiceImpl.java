package com.sensor.service.implementation;

import com.sensor.dao.ICartAddressDao;
import com.sensor.entity.CartAddress;
import com.sensor.service.ICartAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartAddressServiceImpl implements ICartAddressService {

    @Autowired
    private ICartAddressDao cartAddressDao;

    @Override
    public void saveCartAddress(CartAddress cartAddress) {
        this.cartAddressDao.saveCartAddress(cartAddress);
    }
}
