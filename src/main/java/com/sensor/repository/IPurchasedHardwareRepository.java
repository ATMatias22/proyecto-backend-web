package com.sensor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sensor.entity.PurchasedHardware;

public interface IPurchasedHardwareRepository extends JpaRepository<PurchasedHardware, Long> {

	
	
}
