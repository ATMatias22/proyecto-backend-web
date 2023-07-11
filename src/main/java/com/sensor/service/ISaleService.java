package com.sensor.service;

import java.util.List;

import com.sensor.entity.Sale;
import com.sensor.utils.transport.Sale.SaleTransportToService;

public interface ISaleService {

	List<Sale> getAllSales();

	Sale getSaleById(Long saleId);

	void saveSale(SaleTransportToService saleTransportToService);

	void deleteSaleById(Long saleId);

	List<Sale> getAllSalesByUserLoggedIn();

}
