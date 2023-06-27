package com.sensor.service;

import java.util.List;

import com.sensor.dto.sale.request.SaleDTO;

public interface ISaleService {

	List<SaleDTO> getAll();

	SaleDTO getSale(Long saleId);

	void save(SaleDTO saleDTO);

	void delete(Long saleId);

	List<SaleDTO> getAllByUserId(String email);

}
