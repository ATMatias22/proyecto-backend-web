package com.sensor.repository;

import com.sensor.entity.TemporaryCartAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITemporaryCartAddressRepository extends JpaRepository<TemporaryCartAddress,Long> {
}
