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
	private IProductRepository productRepository;
	

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProductById(Long productId) {
		productRepository.updateProductForDisabled(productId);
	}

	@Override
	public Optional<Product> getProductByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> getAllEnabledProducts() {
		return (List<Product>) productRepository.findByEnabledTrue();
	}

	@Override
	public Optional<Product> getEnabledProductById(Long productId) {
		return productRepository.findByEnabledTrueAndProductId(productId);
	}

	@Override
	public Optional<Product> getLastProduct() {
		return productRepository.findTopByOrderByProductIdDesc();
	}
	

}
