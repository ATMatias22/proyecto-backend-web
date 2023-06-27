package com.sensor.DAO.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.DAO.PurchasedHardwareRepository;
import com.sensor.persistence.entity.PurchasedHardware;
import com.sensor.repository.PurchasedHardwareCrudRepository;


@Repository
public class PurchasedHardwareRepositoryImpl implements PurchasedHardwareRepository{

	@Autowired
	private PurchasedHardwareCrudRepository purchasedHardwareCrudRepository;
	
	
	
	@Override
	public List<PurchasedHardware> getAll() {
		return (List<PurchasedHardware>) purchasedHardwareCrudRepository.findAll();
	}

	@Override
	public Optional<PurchasedHardware> getPurchasedHardware(Long purchasedHardwareId) {
		return purchasedHardwareCrudRepository.findById(purchasedHardwareId);
	}

	@Override
	public PurchasedHardware save(PurchasedHardware purchasedHardware) {
		return purchasedHardwareCrudRepository.save(purchasedHardware);
	}

	@Override
	public void delete(Long purchasedHardwareId) {
		 purchasedHardwareCrudRepository.deleteById(purchasedHardwareId);

	}

}
