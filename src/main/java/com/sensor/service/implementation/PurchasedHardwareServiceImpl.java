package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sensor.DAO.PurchasedHardwareRepository;
import com.sensor.DAO.UserRepository;
import com.sensor.dto.PurchasedHardwareDTO;
import com.sensor.exception.BlogAppException;
import com.sensor.mapper.PurchasedHardwareMapper;
import com.sensor.persistence.entity.PurchasedHardware;
import com.sensor.persistence.entity.User;
import com.sensor.service.PurchasedHardwareService;

@Service
public class PurchasedHardwareServiceImpl implements PurchasedHardwareService {

	@Autowired
	private PurchasedHardwareRepository purchasedHardwareRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PurchasedHardwareMapper purchasedHardwareMapper;

	
	@Override
	public List<PurchasedHardwareDTO> getAll() {
		return purchasedHardwareRepository.getAll().stream().map((ph)->purchasedHardwareMapper.toPurchasedHardwareDTO(ph)).collect(Collectors.toList());
	}

	@Override
	public PurchasedHardwareDTO getPurchasedHardware(Long purchasedHardwareId) {
		Optional<PurchasedHardware> opt = purchasedHardwareRepository.getPurchasedHardware(purchasedHardwareId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND,
					"No se encontro el hardware comprado con el id : " + purchasedHardwareId);
		}
		return purchasedHardwareMapper.toPurchasedHardwareDTO(opt.get());
	}

	@Override
	public void save(PurchasedHardwareDTO purchasedHardwareDTO) {

		Optional<User> user = userRepository.getUser(purchasedHardwareDTO.getUserId());

		if (user.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el usuario con id : " + purchasedHardwareDTO.getUserId());
		}
		
		purchasedHardwareRepository.save(purchasedHardwareMapper.toPurchasedHardware(purchasedHardwareDTO));
	}

	@Override
	public void delete(Long purchasedHardwareId) {
		Optional<PurchasedHardware> opt = purchasedHardwareRepository.getPurchasedHardware(purchasedHardwareId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND,
					"No se encontro el hardware comprado con id : " + purchasedHardwareId);
		}

		purchasedHardwareRepository.delete(purchasedHardwareId);
	}


	@Override
	public void modify(Long purchasedHardwareId, PurchasedHardwareDTO purchasedHardwareDTO) {
		
		Optional<User> user = userRepository.getUser(purchasedHardwareDTO.getUserId());
		
		if (user.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el usuario con id : " + purchasedHardwareDTO.getUserId());
		}
		
		
		purchasedHardwareRepository.save(purchasedHardwareMapper.toPurchasedHardwareModify(purchasedHardwareDTO));
		
	}

}
