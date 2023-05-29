package com.sensor.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sensor.DAO.ProductRepository;
import com.sensor.DAO.SaleRepository;
import com.sensor.DAO.UserRepository;
import com.sensor.dto.SaleDTO;
import com.sensor.exception.BlogAppException;
import com.sensor.mapper.SaleMapper;
import com.sensor.persistence.entity.Product;
import com.sensor.persistence.entity.Sale;
import com.sensor.persistence.entity.User;
import com.sensor.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService{

	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private SaleMapper saleMapper;
	
	@Override
	public List<SaleDTO> getAll() {
		return saleRepository.getAll().stream().map((sale)->saleMapper.toSaleDTO(sale)).collect(Collectors.toList());
	}

	@Override
	public SaleDTO getSale(Long saleId) {
		Optional<Sale> opt = saleRepository.getSale(saleId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro la venta con el id : " + saleId);
		}
		return saleMapper.toSaleDTO(opt.get());
	}
	
	@Override
	public List<SaleDTO> getAllByUserId(String email) {
		Optional<User> user =userRepository.getUserByEmail(email);

		if (user.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el usuario con email : " + email);
		}
		
		
		return saleRepository.getAllByUserId(user.get().getUserId()).stream().map((sale)->saleMapper.toSaleDTO(sale)).collect(Collectors.toList());
	}


	@Override
	public void save(SaleDTO saleDTO) {
		
		Optional<Product> product = productRepository.getProductEnabled(saleDTO.getProductId());

		if (product.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el producto: " + saleDTO.getProductId());
		}
		
		Optional<User> user =userRepository.getUserByEmail(saleDTO.getEmail());

		if (user.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro el usuario: " + saleDTO.getUserId());
		}
		
		saleDTO.setUserId(user.get().getUserId());
		
		saleRepository.save(saleMapper.toSale(saleDTO));
	}

	@Override
	public void delete(Long saleId) {
		Optional<Sale> opt = saleRepository.getSale(saleId);

		if (opt.isEmpty()) {
			throw new BlogAppException(HttpStatus.NOT_FOUND, "No se encontro la venta con id : " + saleId);
		}
		
		saleRepository.delete(saleId);	
	}

	


	
}
