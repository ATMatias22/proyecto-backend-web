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
	public List<Sale> getAll() {
		return (List<Sale>) saleRepository.findAll();

	}

	@Override
	public Optional<Sale> getSale(Long saleId) {
		return saleRepository.findById(saleId);
	}

	@Override
	public Sale save(Sale sale) {
		return saleRepository.save(sale);
	}

	@Override
	public void delete(Long saleId) {
		saleRepository.deleteById(saleId);
	}

	@Override
	public List<Sale> getAllByUserId(Long userId) {
		return saleRepository.findByUserId(userId);
	}

}
