package com.sensor.service.implementation;

import com.sensor.dao.IShippingMethodDao;
import com.sensor.entity.ShippingMethod;
import com.sensor.exception.GeneralException;
import com.sensor.service.IShippingMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingMethodServiceImpl implements IShippingMethodService {

    @Autowired
    private IShippingMethodDao shippingMethodDao;

    @Override
    public ShippingMethod getShippingMethodByName(String name) {
        return this.shippingMethodDao.getShippingMethodByName(name).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontró el nombre del método de envío: "+name));
    }

    @Override
    public List<ShippingMethod> getAllShippingMethods() {
        return this.shippingMethodDao.getAllShippingMethods();
    }
}
