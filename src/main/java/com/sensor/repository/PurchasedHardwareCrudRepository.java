package com.sensor.repository;

import org.springframework.data.repository.CrudRepository;

import com.sensor.persistence.entity.PurchasedHardware;

public interface PurchasedHardwareCrudRepository extends CrudRepository<PurchasedHardware, Long> {

	
	
}
