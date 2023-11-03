package com.sensor.dao;

import java.util.List;
import java.util.Optional;

import com.sensor.entity.PurchasedHardware;

public interface IPurchasedHardwareDao {
	
	
	List<PurchasedHardware> getAllPurchasedHardware();

	Optional<PurchasedHardware> getPurchasedHardwareById(Long purchasedHardwareId);

	void savePurchasedHardware(PurchasedHardware purchasedHardware);
	
	void deletePurchasedHardwareById(Long purchasedHardwareId);


}
