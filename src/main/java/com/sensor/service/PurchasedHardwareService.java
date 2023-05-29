package com.sensor.service;

import java.util.List;
import java.util.Optional;

import com.sensor.dto.PurchasedHardwareDTO;
import com.sensor.persistence.entity.PurchasedHardware;

public interface PurchasedHardwareService {
	

	List<PurchasedHardwareDTO> getAll();

	PurchasedHardwareDTO getPurchasedHardware(Long purchasedHardwareId);

	void save(PurchasedHardwareDTO purchasedHardwareDTO);
	
	void delete(Long purchasedHardwareId);

	void modify(Long purchasedHardwareId,PurchasedHardwareDTO purchasedHardwareDTO );

}
