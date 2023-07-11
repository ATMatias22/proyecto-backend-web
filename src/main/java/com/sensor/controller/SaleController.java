package com.sensor.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.sensor.dto.sale.request.SaleRequest;
import com.sensor.dto.sale.response.SaleResponse;
import com.sensor.mapper.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import javax.validation.Valid;


@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "*")
public class SaleController {

	@Autowired
	private ISaleService saleService;

	@Autowired
	private SaleMapper saleMapper;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<SaleResponse>> getAllSales() {
		return new ResponseEntity<>(saleService.getAllSales().stream().map(sale -> this.saleMapper.toSaleResponse(sale)).collect(Collectors.toList()), HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/user-logged-in", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<SaleResponse>> getAllSaleByUserLoggedIn() {
		return new ResponseEntity<>(saleService.getAllSalesByUserLoggedIn().stream().map(sale -> this.saleMapper.toSaleResponse(sale)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> saveSale(@RequestBody @Valid SaleRequest saleRequest) {
		saleService.saveSale(this.saleMapper.toSaleTransportToService(saleRequest));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/{saleId}", produces = {MediaType.APPLICATION_JSON_VALUE})
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
