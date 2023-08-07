package com.sensor.dao.implementation;

import com.sensor.dao.ITemporaryCartAddressDao;
import com.sensor.entity.Cart;
import com.sensor.entity.TemporaryCartAddress;
import com.sensor.repository.ITemporaryCartAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TemporaryCartAddressDaoImpl implements ITemporaryCartAddressDao {

    @Autowired
    private ITemporaryCartAddressRepository temporaryCartAddress;

    @Override
    public void saveTemporaryCartAddress(TemporaryCartAddress temporaryCartAddress) {
        this.temporaryCartAddress.save(temporaryCartAddress);
    }

    @Override
    public void deleteTemporaryCartAddressByCart(Cart cart) {
        this.temporaryCartAddress.deleteTemporaryCartAddressByCart(cart);
    }
}
