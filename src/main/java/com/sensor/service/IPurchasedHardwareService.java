package com.sensor.service;

import java.util.List;

import com.sensor.dto.purchasedHardware.request.PurchasedHardwareDTO;

public interface IPurchasedHardwareService {
	

	List<PurchasedHardwareDTO> getAllPurchasedHardware();

	PurchasedHardwareDTO getPurchasedHardwareById(Long purchasedHardwareId);

	void savePurchasedHardware(PurchasedHardwareDTO purchasedHardwareDTO);
	
	void deletePurchasedHardwareById(Long purchasedHardwareId);

	void modifyPurchasedHardwareById(Long purchasedHardwareId, PurchasedHardwareDTO purchasedHardwareDTO );

}
