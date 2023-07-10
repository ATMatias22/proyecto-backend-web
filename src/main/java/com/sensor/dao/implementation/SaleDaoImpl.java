package com.sensor.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.dao.ISaleDao;
import com.sensor.entity.Sale;
import com.sensor.repository.ISaleRepository;


@Repository
public class SaleDaoImpl implements ISaleDao {

	
	@Autowired
	private ISaleRepository saleRepository;
	
	@Override
	public List<Sale> getAllSales() {
		return (List<Sale>) saleRepository.findAll();

	}

	@Override
	public Optional<Sale> getSaleById(Long saleId) {
		return saleRepository.findById(saleId);
	}

	@Override
	public void saveSale(Sale sale) {
		saleRepository.save(sale);
	}

	@Override
	public void deleteSaleById(Long saleId) {
		saleRepository.deleteById(saleId);
	}

	@Override
	public List<Sale> getAllSalesByUserId(Long userId) {
		return saleRepository.findByUserId(userId);
	}

}
