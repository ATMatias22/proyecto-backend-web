package com.sensor.dao;

import java.util.List;
import java.util.Optional;

import com.sensor.entity.Sale;

public interface ISaleDao {
	
	List<Sale> getAllSales();
	
	List<Sale> getAllSalesByUserId(Long userId);

	Optional<Sale> getSaleById(Long saleId);

	Sale saveSale(Sale sale);
	
	void deleteSaleById(Long saleId);
	
}
