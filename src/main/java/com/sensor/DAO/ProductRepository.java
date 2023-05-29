package com.sensor.DAO;

import java.util.List;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.sensor.persistence.entity.Product;
public interface ProductRepository {
	
	List<Product> getAllEnabled();
	
	Optional<Product> getProductEnabled(Long productId);
	
	Product save(Product product);
	
	void delete (Long productId);
	
	Optional<Product> getProductByName(String name);

	Optional<Product> getLastProduct();

}
