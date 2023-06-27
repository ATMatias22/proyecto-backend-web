package com.sensor.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.dao.IProductDao;
import com.sensor.entity.Product;
import com.sensor.repository.IProductRepository;

@Repository
public class ProductDaoImpl implements IProductDao {

	
	@Autowired
	private IProductRepository IProductRepository;
	

	@Override
	public Product save(Product product) {
		return IProductRepository.save(product);
	}

	@Override
	public void delete(Long productId) {
		IProductRepository.updateProductForDisabled(productId);
	}

	@Override
	public Optional<Product> getProductByName(String name) {
		return IProductRepository.findByName(name);
	}

	@Override
	public List<Product> getAllEnabled() {
		return (List<Product>) IProductRepository.findByEnabledTrue();
	}

	@Override
	public Optional<Product> getProductEnabled(Long productId) {
		return IProductRepository.findByEnabledTrueAndProductId(productId);
	}

	@Override
	public Optional<Product> getLastProduct() {
		return IProductRepository.findTopByOrderByProductIdDesc();
	}
	

}
