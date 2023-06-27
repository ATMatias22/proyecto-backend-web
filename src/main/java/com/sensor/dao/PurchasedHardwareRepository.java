package com.sensor.DAO;

import java.util.List;
import java.util.Optional;

import com.sensor.persistence.entity.PurchasedHardware;

public interface PurchasedHardwareRepository {
	
	
	List<PurchasedHardware> getAll();

	Optional<PurchasedHardware> getPurchasedHardware(Long purchasedHardwareId);

	PurchasedHardware save(PurchasedHardware purchasedHardware);
	
	void delete(Long purchasedHardwareId);


}
