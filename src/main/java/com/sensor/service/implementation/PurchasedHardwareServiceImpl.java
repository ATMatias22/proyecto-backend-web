package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sensor.security.MainUser;
import com.sensor.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sensor.dao.IPurchasedHardwareDao;
import com.sensor.security.dao.IUserDao;
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
	private IUserService userService;

	
	@Override
	public List<PurchasedHardware> getAllPurchasedHardware() {
		return purchasedHardwareDao.getAllPurchasedHardware();
	}

	@Override
	public PurchasedHardware getPurchasedHardwareById(Long purchasedHardwareId) {
		return purchasedHardwareDao.getPurchasedHardwareById(purchasedHardwareId).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND,
				"No se encontro el hardware comprado con el id : " + purchasedHardwareId));
	}

	@Override
	public void savePurchasedHardware(PurchasedHardware purchasedHardware) {
		MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = this.userService.getUserByEmail(mu.getUsername());
		purchasedHardware.setUser(user);
		purchasedHardwareDao.savePurchasedHardware(purchasedHardware);
	}

	@Override
	public void deletePurchasedHardwareById(Long purchasedHardwareId) {
		this.getPurchasedHardwareById(purchasedHardwareId);
		purchasedHardwareDao.deletePurchasedHardwareById(purchasedHardwareId);
	}


	@Override
	public void modifyPurchasedHardwareById(Long purchasedHardwareId, PurchasedHardware purchasedHardware) {
		MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		PurchasedHardware purchasedHardwareSearched = this.getPurchasedHardwareById(purchasedHardwareId);
		if(!purchasedHardwareSearched.getUser().getUserId().equals(mu.getId())){
			throw new GeneralException(HttpStatus.UNAUTHORIZED, "Solo el usuario que creó este producto comprado puede modificarlo");
		}
		purchasedHardwareSearched.setName(purchasedHardware.getName());
		purchasedHardwareSearched.setQuantity(purchasedHardware.getQuantity());
		purchasedHardwareSearched.setDatePurchase(purchasedHardware.getDatePurchase());
		purchasedHardwareSearched.setProvider(purchasedHardware.getProvider());
		purchasedHardwareSearched.setPrice(purchasedHardware.getPrice());

		purchasedHardwareDao.savePurchasedHardware(purchasedHardwareSearched);

	}

}
