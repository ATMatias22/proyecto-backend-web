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
	private ISaleRepository ISaleRepository;
	
	@Override
	public List<Sale> getAll() {
		return (List<Sale>) ISaleRepository.findAll();

	}

	@Override
	public Optional<Sale> getSale(Long saleId) {
		return ISaleRepository.findById(saleId);
	}

	@Override
	public Sale save(Sale sale) {
		return ISaleRepository.save(sale);
	}

	@Override
	public void delete(Long saleId) {
		ISaleRepository.deleteById(saleId);
	}

	@Override
	public List<Sale> getAllByUserId(Long userId) {
		return ISaleRepository.findByUserId(userId);
	}

}
