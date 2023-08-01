package com.sensor.service.implementation;

import com.sensor.dao.ITypeOfAddressDao;
import com.sensor.entity.TypeOfAddress;
import com.sensor.exception.GeneralException;
import com.sensor.service.ITypeOfAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TypeOfAddressServiceImpl implements ITypeOfAddressService {

    @Autowired
    private ITypeOfAddressDao typeOfAddressDao;

    @Override
    public TypeOfAddress getTypeOfAddressByName(String name) {
        return this.typeOfAddressDao.getTypeOfAddressByName(name).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro el tipo de direccion con nombre: "+ name));
    }
}
