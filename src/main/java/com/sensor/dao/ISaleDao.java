package com.sensor.dao;

import java.util.List;
import java.util.Optional;

import com.sensor.entity.Sale;
import com.sensor.security.entity.User;

public interface ISaleDao {
	
	List<Sale> getAllSales();
	
	List<Sale> getAllSalesByUser(User user);

	Optional<Sale> getSaleById(Long saleId);

	void saveSale(Sale sale);
	
	void deleteSaleById(Long saleId);
	
}
