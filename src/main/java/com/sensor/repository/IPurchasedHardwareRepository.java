package com.sensor.repository;

import org.springframework.data.repository.CrudRepository;

import com.sensor.entity.PurchasedHardware;

public interface IPurchasedHardwareRepository extends CrudRepository<PurchasedHardware, Long> {

	
	
}
