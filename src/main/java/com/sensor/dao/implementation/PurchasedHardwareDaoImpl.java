package com.sensor.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.dao.IPurchasedHardwareDao;
import com.sensor.entity.PurchasedHardware;
import com.sensor.repository.PurchasedHardwareCrudRepository;


@Repository
public class PurchasedHardwareDaoImpl implements IPurchasedHardwareDao {

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
