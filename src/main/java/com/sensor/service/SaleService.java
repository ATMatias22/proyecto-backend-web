package com.sensor.service;

import java.util.List;
import java.util.Optional;

import com.sensor.dto.SaleDTO;
import com.sensor.persistence.entity.Sale;

public interface SaleService {

	List<SaleDTO> getAll();

	SaleDTO getSale(Long saleId);

	void save(SaleDTO saleDTO);

	void delete(Long saleId);

	List<SaleDTO> getAllByUserId(String email);

}
