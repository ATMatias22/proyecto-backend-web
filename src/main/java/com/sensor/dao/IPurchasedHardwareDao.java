package com.sensor.dao;

import java.util.List;
import java.util.Optional;

import com.sensor.entity.PurchasedHardware;

public interface IPurchasedHardwareDao {
	
	
	List<PurchasedHardware> getAll();

	Optional<PurchasedHardware> getPurchasedHardware(Long purchasedHardwareId);

	PurchasedHardware save(PurchasedHardware purchasedHardware);
	
	void delete(Long purchasedHardwareId);


}
