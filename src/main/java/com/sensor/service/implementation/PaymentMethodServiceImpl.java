package com.sensor.service.implementation;

import com.sensor.dao.IPaymentMethodDao;
import com.sensor.entity.PaymentMethod;
import com.sensor.exception.GeneralException;
import com.sensor.service.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements IPaymentMethodService {

    @Autowired
    private IPaymentMethodDao paymentMethodDao;

    @Override
    public PaymentMethod getPaymentMethodByName(String name) {
        return this.paymentMethodDao.getPaymentMethodByName(name).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el nombre del metodo de pago: "+name));
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return this.paymentMethodDao.getAllPaymentMethods();
    }
}
