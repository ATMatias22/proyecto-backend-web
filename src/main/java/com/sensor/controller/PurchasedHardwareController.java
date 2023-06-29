package com.sensor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sensor.dto.purchasedHardware.request.PurchasedHardwareDTO;
import com.sensor.service.IPurchasedHardwareService;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/purchasedhardwares")
@CrossOrigin(origins = "*")
public class PurchasedHardwareController {
	
	
	@Autowired
	private IPurchasedHardwareService purchasedHardwareService;
	
	@GetMapping("/all")
	public ResponseEntity<List<PurchasedHardwareDTO>> getAllPurchasedHardware() {
		return new ResponseEntity<>(purchasedHardwareService.getAllPurchasedHardware(), HttpStatus.OK);
	}
	
	
	@GetMapping("/{purchasedHardwareServiceId}")
	public ResponseEntity<PurchasedHardwareDTO> getPurchasedHardwareById(
			@PathVariable("purchasedHardwareServiceId") Long purchasedHardwareServiceId) {
		return new ResponseEntity<PurchasedHardwareDTO>(purchasedHardwareService.getPurchasedHardwareById(purchasedHardwareServiceId), HttpStatus.OK);
	}
	
	
	
	@PostMapping
	public ResponseEntity<PurchasedHardwareDTO> savePurchasedHardware(@RequestBody PurchasedHardwareDTO purchasedHardwareDTO) {
		purchasedHardwareService.savePurchasedHardware(purchasedHardwareDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{purchasedHardwareId}")
	public ResponseEntity deletePurchasedHardwareById(@PathVariable("purchasedHardwareId") Long purchasedHardwareId) {
		purchasedHardwareService.deletePurchasedHardwareById(purchasedHardwareId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping("/{purchasedHardwareId}")
	public ResponseEntity modifyPurchasedHardwareById(@PathVariable("purchasedHardwareId") Long purchasedHardwareId, @RequestBody PurchasedHardwareDTO purchasedHardwareDTO) {
		purchasedHardwareService.modifyPurchasedHardwareById(purchasedHardwareId,purchasedHardwareDTO);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	

}
