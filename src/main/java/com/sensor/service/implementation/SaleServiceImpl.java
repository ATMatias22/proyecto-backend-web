package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sensor.dao.IProductDao;
import com.sensor.dao.ISaleDao;
import com.sensor.dao.IUserDao;
import com.sensor.dto.sale.request.SaleDTO;
import com.sensor.exception.BlogAppException;
import com.sensor.mapper.SaleMapper;
import com.sensor.entity.Product;
import com.sensor.entity.Sale;
import com.sensor.entity.User;
import com.sensor.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService{

	
	@Autowired
	private IUserDao IUserDao;
	@Autowired
	private IProductDao IProductDao;
	@Autowired
	private ISaleDao ISaleDao;
	
	@Autowired
	private SaleMapper saleMapper;
	
	@Override
	public List<SaleDTO> getAll() {
		return ISaleDao.getAll().stream().map((sale)->saleMapper.toSaleDTO(sale)).collect(Collectors.toList());
	}

	@Override
	public SaleDTO getSale(Long saleId) {
		Optional<Sale> opt = ISaleDao.getSale(saleId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro la venta con el id : " + saleId);
		}
		return saleMapper.toSaleDTO(opt.get());
	}
	
	@Override
	public List<SaleDTO> getAllByUserId(String email) {
		Optional<User> user = IUserDao.getUserByEmail(email);

		if (user.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el usuario con email : " + email);
		}
		
		
		return ISaleDao.getAllByUserId(user.get().getUserId()).stream().map((sale)->saleMapper.toSaleDTO(sale)).collect(Collectors.toList());
	}


	@Override
	public void save(SaleDTO saleDTO) {
		
		Optional<Product> product = IProductDao.getProductEnabled(saleDTO.getProductId());

		if (product.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el producto: " + saleDTO.getProductId());
		}
		
		Optional<User> user = IUserDao.getUserByEmail(saleDTO.getEmail());

		if (user.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el usuario: " + saleDTO.getUserId());
		}
		
		saleDTO.setUserId(user.get().getUserId());
		
		ISaleDao.save(saleMapper.toSale(saleDTO));
	}

	@Override
	public void delete(Long saleId) {
		Optional<Sale> opt = ISaleDao.getSale(saleId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro la venta con id : " + saleId);
		}
		
		ISaleDao.delete(saleId);
	}

	


	
}
