package com.sensor.DAO.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sensor.DAO.SaleRepository;
import com.sensor.persistence.entity.Sale;
import com.sensor.repository.SaleCrudRepository;


@Repository
public class SaleRepositoryImpl implements SaleRepository{

	
	@Autowired
	private SaleCrudRepository saleCrudRepository;
	
	@Override
	public List<Sale> getAll() {
		return (List<Sale>) saleCrudRepository.findAll();

	}

	@Override
	public Optional<Sale> getSale(Long saleId) {
		return saleCrudRepository.findById(saleId);
	}

	@Override
	public Sale save(Sale sale) {
		return saleCrudRepository.save(sale);
	}

	@Override
	public void delete(Long saleId) {
		saleCrudRepository.deleteById(saleId);
	}

	@Override
	public List<Sale> getAllByUserId(Long userId) {
		return saleCrudRepository.findByUserId(userId);
	}

}
