package com.sensor.dao;

import java.util.List;

import java.util.Optional;

import com.sensor.entity.Product;
public interface IProductDao {
	
	List<Product> getAllEnabledProducts();
	
	Optional<Product> getEnabledProductById(Long productId);
	
	Product saveProduct(Product product);
	
	void deleteProductById(Long productId);
	
	Optional<Product> getProductByName(String name);

	Optional<Product> getLastProduct();

}
