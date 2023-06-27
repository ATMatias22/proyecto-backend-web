package com.sensor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sensor.entity.Sale;

public interface ISaleRepository extends CrudRepository<Sale, Long>{

	public List<Sale> findByUserId(Long userId);

	
}
