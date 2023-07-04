package com.sensor.controller;

import java.util.List;

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

import com.sensor.dto.sale.request.SaleDTO;
import com.sensor.service.ISaleService;



@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "*")
public class SaleController {

	@Autowired
	private ISaleService saleService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<List<SaleDTO>> getAllSales() {
		return new ResponseEntity<>(saleService.getAllSales(), HttpStatus.OK);
	}

	@PreAuthorize("isAuthenticated() and #email == authentication.principal.username")
	@GetMapping("/user/{email}")
	public ResponseEntity<List<SaleDTO>> getAllSaleByUserEmail(@PathVariable("email") String email) {
		
		return new ResponseEntity<>(saleService.getAllSalesByUserEmail(email), HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated() and #saleDTO.email == authentication.principal.username")
	@PostMapping
	public ResponseEntity saveSale(@RequestBody SaleDTO saleDTO) {
		saleService.saveSale(saleDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{saleId}")
	public ResponseEntity<SaleDTO> getSaleById(
			@PathVariable("saleId") Long saleId) {
		return new ResponseEntity<SaleDTO>(saleService.getSaleById(saleId), HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{saleId}")
	public ResponseEntity deleteSaleById(@PathVariable("saleId") Long saleId) {
		saleService.deleteSaleById(saleId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
}