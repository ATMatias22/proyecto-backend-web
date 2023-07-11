package com.sensor.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.sensor.dto.sale.request.SaleRequest;
import com.sensor.dto.sale.response.SaleResponse;
import com.sensor.mapper.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sensor.service.ISaleService;



@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "*")
public class SaleController {

	@Autowired
	private ISaleService saleService;

	@Autowired
	private SaleMapper saleMapper;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<List<SaleResponse>> getAllSales() {
		return new ResponseEntity<>(saleService.getAllSales().stream().map(sale -> this.saleMapper.toSaleResponse(sale)).collect(Collectors.toList()), HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/user-logged-in")
	public ResponseEntity<List<SaleResponse>> getAllSaleByUserLoggedIn() {
		return new ResponseEntity<>(saleService.getAllSalesByUserLoggedIn().stream().map(sale -> this.saleMapper.toSaleResponse(sale)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping
	public ResponseEntity<Void> saveSale(@RequestBody SaleRequest saleRequest) {
		saleService.saveSale(this.saleMapper.toSaleTransportToService(saleRequest));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{saleId}")
	public ResponseEntity<SaleResponse> getSaleById(
			@PathVariable("saleId") Long saleId) {
		return new ResponseEntity<SaleResponse>(this.saleMapper.toSaleResponse(saleService.getSaleById(saleId)), HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{saleId}")
	public ResponseEntity<Void> deleteSaleById(@PathVariable("saleId") Long saleId) {
		saleService.deleteSaleById(saleId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
