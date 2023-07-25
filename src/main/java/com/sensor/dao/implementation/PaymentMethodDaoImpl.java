package com.sensor.dao.implementation;

import com.sensor.dao.IPaymentMethodDao;
import com.sensor.entity.PaymentMethod;
import com.sensor.repository.IPaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PaymentMethodDaoImpl implements IPaymentMethodDao {
    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;

    @Override
    public Optional<PaymentMethod> getPaymentMethodByName(String name) {
        return this.paymentMethodRepository.findByName(name);
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return this.paymentMethodRepository.findAll();
    }
}
