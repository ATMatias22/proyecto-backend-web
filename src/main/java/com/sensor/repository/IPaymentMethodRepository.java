package com.sensor.repository;

import com.sensor.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPaymentMethodRepository extends JpaRepository<PaymentMethod,Long> {

    Optional<PaymentMethod> findByName(String name);
}
