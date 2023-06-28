package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sensor.dao.IPurchasedHardwareDao;
import com.sensor.dao.IUserDao;
import com.sensor.dto.purchasedHardware.request.PurchasedHardwareDTO;
import com.sensor.exception.BlogAppException;
import com.sensor.mapper.PurchasedHardwareMapper;
import com.sensor.entity.PurchasedHardware;
import com.sensor.security.entity.User;
import com.sensor.service.IPurchasedHardwareService;

@Service
public class PurchasedHardwareServiceImpl implements IPurchasedHardwareService {

	@Autowired
	private IPurchasedHardwareDao IPurchasedHardwareDao;

	@Autowired
	private IUserDao IUserDao;
	
	@Autowired
	private PurchasedHardwareMapper purchasedHardwareMapper;

	
	@Override
	public List<PurchasedHardwareDTO> getAll() {
		return IPurchasedHardwareDao.getAll().stream().map((ph)->purchasedHardwareMapper.toPurchasedHardwareDTO(ph)).collect(Collectors.toList());
	}

	@Override
	public PurchasedHardwareDTO getPurchasedHardware(Long purchasedHardwareId) {
		Optional<PurchasedHardware> opt = IPurchasedHardwareDao.getPurchasedHardware(purchasedHardwareId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND,
					"No se encontro el hardware comprado con el id : " + purchasedHardwareId);
		}
		return purchasedHardwareMapper.toPurchasedHardwareDTO(opt.get());
	}

	@Override
	public void save(PurchasedHardwareDTO purchasedHardwareDTO) {

		Optional<User> user = IUserDao.getUser(purchasedHardwareDTO.getUserId());

		if (user.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el usuario con id : " + purchasedHardwareDTO.getUserId());
		}
		
		IPurchasedHardwareDao.save(purchasedHardwareMapper.toPurchasedHardware(purchasedHardwareDTO));
	}

	@Override
	public void delete(Long purchasedHardwareId) {
		Optional<PurchasedHardware> opt = IPurchasedHardwareDao.getPurchasedHardware(purchasedHardwareId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND,
					"No se encontro el hardware comprado con id : " + purchasedHardwareId);
		}

		IPurchasedHardwareDao.delete(purchasedHardwareId);
	}


	@Override
	public void modify(Long purchasedHardwareId, PurchasedHardwareDTO purchasedHardwareDTO) {
		
		Optional<User> user = IUserDao.getUser(purchasedHardwareDTO.getUserId());
		
		if (user.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el usuario con id : " + purchasedHardwareDTO.getUserId());
		}
		
		
		IPurchasedHardwareDao.save(purchasedHardwareMapper.toPurchasedHardwareModify(purchasedHardwareDTO));
		
	}

}
