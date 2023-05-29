package com.sensor.DAO;

import java.util.List;
import java.util.Optional;

import com.sensor.persistence.entity.Sale;

public interface SaleRepository {
	
	List<Sale> getAll();
	
	List<Sale> getAllByUserId(Long userId);

	Optional<Sale> getSale(Long saleId);

	Sale save(Sale sale);
	
	void delete(Long saleId);
	
}
