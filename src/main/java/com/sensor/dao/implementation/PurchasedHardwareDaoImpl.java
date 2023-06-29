package com.sensor.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.dao.IPurchasedHardwareDao;
import com.sensor.entity.PurchasedHardware;
import com.sensor.repository.IPurchasedHardwareRepository;


@Repository
public class PurchasedHardwareDaoImpl implements IPurchasedHardwareDao {

	@Autowired
	private IPurchasedHardwareRepository purchasedHardwareRepository;
	
	
	
	@Override
	public List<PurchasedHardware> getAllPurchasedHardware() {
		return (List<PurchasedHardware>) purchasedHardwareRepository.findAll();
	}

	@Override
	public Optional<PurchasedHardware> getPurchasedHardwareById(Long purchasedHardwareId) {
		return purchasedHardwareRepository.findById(purchasedHardwareId);
	}

	@Override
	public PurchasedHardware savePurchasedHardware(PurchasedHardware purchasedHardware) {
		return purchasedHardwareRepository.save(purchasedHardware);
	}

	@Override
	public void deletePurchasedHardwareById(Long purchasedHardwareId) {
		 purchasedHardwareRepository.deleteById(purchasedHardwareId);

	}

}
