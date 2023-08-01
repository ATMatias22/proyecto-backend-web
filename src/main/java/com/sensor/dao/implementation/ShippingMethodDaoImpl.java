package com.sensor.dao.implementation;

import com.sensor.dao.IShippingMethodDao;
import com.sensor.entity.ShippingMethod;
import com.sensor.repository.IShippingMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ShippingMethodDaoImpl implements IShippingMethodDao {

    @Autowired
    private IShippingMethodRepository shippingMethodRepository;

    @Override
    public Optional<ShippingMethod> getShippingMethodByName(String name) {
        return this.shippingMethodRepository.findByName(name);
    }

    @Override
    public List<ShippingMethod> getAllShippingMethods() {
        return this.shippingMethodRepository.findAll();
    }
}
