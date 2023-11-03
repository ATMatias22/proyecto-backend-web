package com.sensor.dao.implementation;

import com.sensor.dao.ITypeOfAddressDao;
import com.sensor.entity.TypeOfAddress;
import com.sensor.repository.ITypeOfAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TypeOfAddressDaoImpl implements ITypeOfAddressDao {

    @Autowired
    private ITypeOfAddressRepository typeOfAddressRepository;

    @Override
    public Optional<TypeOfAddress> getTypeOfAddressByName(String name) {
        return typeOfAddressRepository.findByName(name);
    }

}
