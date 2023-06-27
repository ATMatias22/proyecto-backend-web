package com.sensor.service;

import java.util.List;

import com.sensor.dto.purchasedHardware.request.PurchasedHardwareDTO;

public interface PurchasedHardwareService {
	

	List<PurchasedHardwareDTO> getAll();

	PurchasedHardwareDTO getPurchasedHardware(Long purchasedHardwareId);

	void save(PurchasedHardwareDTO purchasedHardwareDTO);
	
	void delete(Long purchasedHardwareId);

	void modify(Long purchasedHardwareId,PurchasedHardwareDTO purchasedHardwareDTO );

}
