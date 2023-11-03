package com.sensor.repository;

import com.sensor.entity.ShippingMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IShippingMethodRepository extends JpaRepository<ShippingMethod, Long> {

    Optional<ShippingMethod> findByName(String name);

}
