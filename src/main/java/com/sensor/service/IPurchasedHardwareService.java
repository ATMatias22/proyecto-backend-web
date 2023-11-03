package com.sensor.service;

import java.util.List;
import com.sensor.entity.PurchasedHardware;

public interface IPurchasedHardwareService {
	

	List<PurchasedHardware> getAllPurchasedHardware();

	PurchasedHardware getPurchasedHardwareById(Long purchasedHardwareId);

	void savePurchasedHardware(PurchasedHardware purchasedHardware);
	
	void deletePurchasedHardwareById(Long purchasedHardwareId);

	void modifyPurchasedHardwareById(Long purchasedHardwareId, PurchasedHardware purchasedHardware);

}
