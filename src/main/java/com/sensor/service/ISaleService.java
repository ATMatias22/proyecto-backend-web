package com.sensor.service;

import java.util.List;

import com.sensor.dto.sale.request.SaleDTO;

public interface ISaleService {

	List<SaleDTO> getAllSales();

	SaleDTO getSaleById(Long saleId);

	void saveSale(SaleDTO saleDTO);

	void deleteSaleById(Long saleId);

	List<SaleDTO> getAllSalesByUserEmail(String email);

}
