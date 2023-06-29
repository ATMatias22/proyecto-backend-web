package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sensor.dao.IPurchasedHardwareDao;
import com.sensor.security.dao.IUserDao;
import com.sensor.dto.purchasedHardware.request.PurchasedHardwareDTO;
import com.sensor.exception.GeneralException;
import com.sensor.mapper.PurchasedHardwareMapper;
import com.sensor.entity.PurchasedHardware;
import com.sensor.security.entity.User;
import com.sensor.service.IPurchasedHardwareService;

@Service
public class PurchasedHardwareServiceImpl implements IPurchasedHardwareService {

	@Autowired
	private IPurchasedHardwareDao purchasedHardwareDao;

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private PurchasedHardwareMapper purchasedHardwareMapper;

	
	@Override
	public List<PurchasedHardwareDTO> getAllPurchasedHardware() {
		return purchasedHardwareDao.getAllPurchasedHardware().stream().map((ph)->purchasedHardwareMapper.toPurchasedHardwareDTO(ph)).collect(Collectors.toList());
	}

	@Override
	public PurchasedHardwareDTO getPurchasedHardwareById(Long purchasedHardwareId) {
		Optional<PurchasedHardware> opt = purchasedHardwareDao.getPurchasedHardwareById(purchasedHardwareId);

		if (opt.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND,
					"No se encontro el hardware comprado con el id : " + purchasedHardwareId);
		}
		return purchasedHardwareMapper.toPurchasedHardwareDTO(opt.get());
	}

	@Override
	public void savePurchasedHardware(PurchasedHardwareDTO purchasedHardwareDTO) {

		Optional<User> user = userDao.getUserById(purchasedHardwareDTO.getUserId());

		if (user.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el usuario con id : " + purchasedHardwareDTO.getUserId());
		}
		
		purchasedHardwareDao.savePurchasedHardware(purchasedHardwareMapper.toPurchasedHardware(purchasedHardwareDTO));
	}

	@Override
	public void deletePurchasedHardwareById(Long purchasedHardwareId) {
		Optional<PurchasedHardware> opt = purchasedHardwareDao.getPurchasedHardwareById(purchasedHardwareId);

		if (opt.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND,
					"No se encontro el hardware comprado con id : " + purchasedHardwareId);
		}

		purchasedHardwareDao.deletePurchasedHardwareById(purchasedHardwareId);
	}


	@Override
	public void modifyPurchasedHardwareById(Long purchasedHardwareId, PurchasedHardwareDTO purchasedHardwareDTO) {
		
		Optional<User> user = userDao.getUserById(purchasedHardwareDTO.getUserId());
		
		if (user.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el usuario con id : " + purchasedHardwareDTO.getUserId());
		}
		
		
		purchasedHardwareDao.savePurchasedHardware(purchasedHardwareMapper.toPurchasedHardwareModify(purchasedHardwareDTO));
		
	}

}
