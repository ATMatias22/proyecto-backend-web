package com.sensor.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sensor.entity.Product;

public interface IProductRepository extends CrudRepository<Product, Long> {

	Optional<Product> findByName(String name);

	List<Product> findByEnabledTrue();

	Optional<Product> findByEnabledTrueAndProductId(Long productId);

	@Modifying
	@Transactional
	@Query("update Product p set p.enabled = false where p.productId = ?1")
	void updateProductForDisabled(Long productId);
	
	 Optional<Product> findTopByOrderByProductIdDesc();
	

}
