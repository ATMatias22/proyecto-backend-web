package com.sensor.repository;

import com.sensor.entity.TypeOfAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITypeOfAddressRepository extends JpaRepository<TypeOfAddress, Long> {

    Optional<TypeOfAddress> findByName(String name);

}
