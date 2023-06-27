package com.sensor.dao;

import java.util.List;
import java.util.Optional;

import com.sensor.entity.Sale;

public interface ISaleDao {
	
	List<Sale> getAll();
	
	List<Sale> getAllByUserId(Long userId);

	Optional<Sale> getSale(Long saleId);

	Sale save(Sale sale);
	
	void delete(Long saleId);
	
}
