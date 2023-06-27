package com.sensor.dao;

import java.util.List;

import java.util.Optional;

import com.sensor.entity.Product;
public interface IProductDao {
	
	List<Product> getAllEnabled();
	
	Optional<Product> getProductEnabled(Long productId);
	
	Product save(Product product);
	
	void delete (Long productId);
	
	Optional<Product> getProductByName(String name);

	Optional<Product> getLastProduct();

}
