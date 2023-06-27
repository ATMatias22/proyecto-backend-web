package com.sensor.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.dao.IProductDao;
import com.sensor.persistence.entity.Product;
import com.sensor.repository.ProductCrudRepository;

@Repository
public class ProductDaoImpl implements IProductDao {

	
	@Autowired
	private ProductCrudRepository productCrudRepository;
	

	@Override
	public Product save(Product product) {
		return productCrudRepository.save(product);
	}

	@Override
	public void delete(Long productId) {
		productCrudRepository.updateProductForDisabled(productId);
	}

	@Override
	public Optional<Product> getProductByName(String name) {
		return productCrudRepository.findByName(name);
	}

	@Override
	public List<Product> getAllEnabled() {
		return (List<Product>) productCrudRepository.findByEnabledTrue();
	}

	@Override
	public Optional<Product> getProductEnabled(Long productId) {
		return productCrudRepository.findByEnabledTrueAndProductId(productId);
	}

	@Override
	public Optional<Product> getLastProduct() {
		return productCrudRepository.findTopByOrderByProductIdDesc();
	}
	

}
