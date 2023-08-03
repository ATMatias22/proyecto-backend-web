package com.sensor.dao.implementation;

import com.sensor.dao.ICartAddressDao;
import com.sensor.entity.CartAddress;
import com.sensor.repository.ICartAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartAddressDaoImpl implements ICartAddressDao {

    @Autowired
    private ICartAddressRepository cartAddressRepository;

    @Override
    public void saveCartAddress(CartAddress cartAddress) {
        this.cartAddressRepository.save(cartAddress);
    }
}
