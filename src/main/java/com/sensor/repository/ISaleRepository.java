package com.sensor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sensor.entity.Sale;

public interface ISaleRepository extends JpaRepository<Sale, Long> {

	List<Sale> findByUserId(Long userId);

	
}
