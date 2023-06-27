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
	private IPurchasedHardwareRepository IPurchasedHardwareRepository;
	
	
	
	@Override
	public List<PurchasedHardware> getAll() {
		return (List<PurchasedHardware>) IPurchasedHardwareRepository.findAll();
	}

	@Override
	public Optional<PurchasedHardware> getPurchasedHardware(Long purchasedHardwareId) {
		return IPurchasedHardwareRepository.findById(purchasedHardwareId);
	}

	@Override
	public PurchasedHardware save(PurchasedHardware purchasedHardware) {
		return IPurchasedHardwareRepository.save(purchasedHardware);
	}

	@Override
	public void delete(Long purchasedHardwareId) {
		 IPurchasedHardwareRepository.deleteById(purchasedHardwareId);

	}

}
