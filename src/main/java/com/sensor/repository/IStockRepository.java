package com.sensor.repository;

import com.sensor.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStockRepository extends JpaRepository<Stock, Long> {


    boolean existsByDeviceCode(String deviceCode);
}
