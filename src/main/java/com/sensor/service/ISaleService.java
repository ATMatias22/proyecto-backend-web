package com.sensor.service;

import java.util.List;

import com.sensor.entity.Sale;

public interface ISaleService {

	List<Sale> getAllSales();

	Sale getSaleById(Long saleId);

	void saveSale(Sale sale);

	void deleteSaleById(Long saleId);

	List<Sale> getAllSalesByUserEmail(String email);

}
