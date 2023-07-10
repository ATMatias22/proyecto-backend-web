package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sensor.dao.IProductDao;
import com.sensor.dao.ISaleDao;
import com.sensor.security.dao.IUserDao;
import com.sensor.exception.GeneralException;
import com.sensor.mapper.SaleMapper;
import com.sensor.entity.Product;
import com.sensor.entity.Sale;
import com.sensor.security.entity.User;
import com.sensor.service.ISaleService;

@Service
public class SaleServiceImpl implements ISaleService {

	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IProductDao productDao;
	@Autowired
	private ISaleDao saleDao;
	
	@Autowired
	private SaleMapper saleMapper;
	
	@Override
	public List<Sale> getAllSales() {
		return saleDao.getAllSales().stream().map((sale)->saleMapper.toSaleDTO(sale)).collect(Collectors.toList());
	}

	@Override
	public Sale getSaleById(Long saleId) {
		Optional<Sale> opt = saleDao.getSaleById(saleId);

		if (opt.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND, "No se encontro la venta con el id : " + saleId);
		}
		return saleMapper.toSaleDTO(opt.get());
	}
	
	@Override
	public List<Sale> getAllSalesByUserEmail(String email) {
		Optional<User> user = userDao.getUserByEmail(email);

		if (user.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el usuario con email : " + email);
		}
		
		
		return saleDao.getAllSalesByUserId(user.get().getUserId()).stream().map((sale)->saleMapper.toSaleDTO(sale)).collect(Collectors.toList());
	}


	@Override
	public void saveSale(Sale sale) {
		
		Optional<Product> product = productDao.getEnabledProductById(sale.getProductId());

		if (product.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el producto: " + sale.getProductId());
		}
		
		Optional<User> user = userDao.getUserByEmail(sale.getEmail());

		if (user.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el usuario: " + sale.getUserId());
		}
		
		sale.setUserId(user.get().getUserId());
		
		saleDao.saveSale(saleMapper.toSale(sale));
	}

	@Override
	public void deleteSaleById(Long saleId) {
		Optional<Sale> opt = saleDao.getSaleById(saleId);

		if (opt.isEmpty()) {
			throw new GeneralException(HttpStatus.NOT_FOUND, "No se encontro la venta con id : " + saleId);
		}
		
		saleDao.deleteSaleById(saleId);
	}

	


	
}
