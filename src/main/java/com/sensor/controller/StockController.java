package com.sensor.controller;

import com.sensor.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/stocks")
@CrossOrigin(origins = "*")
public class StockController {

    @Autowired
    private IStockService stockService;

    @PreAuthorize("hasRole('ADMIN')")
	@PatchMapping(value = "/{stockId}")
	public ResponseEntity<Void> changePlacedOnAPhysicalDeviceInStock(@PathVariable("stockId") Long stockId) {
		this.stockService.changePlacedOnAPhysicalDeviceInStock(stockId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


}
