package com.sensor.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.sensor.dto.purchasedHardware.request.PurchasedHardwareRequest;
import com.sensor.dto.purchasedHardware.response.PurchasedHardwareResponse;
import com.sensor.mapper.PurchasedHardwareMapper;
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

import com.sensor.service.IPurchasedHardwareService;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/purchased-hardwares")
@CrossOrigin(origins = "*")
public class PurchasedHardwareController {
	
	
	@Autowired
	private IPurchasedHardwareService purchasedHardwareService;

	@Autowired
	private PurchasedHardwareMapper purchasedHardwareMapper;
	
	@GetMapping("/all")
	public ResponseEntity<List<PurchasedHardwareResponse>> getAllPurchasedHardware() {
		return new ResponseEntity<>(purchasedHardwareService.getAllPurchasedHardware().stream().map((ph) -> this.purchasedHardwareMapper.toPurchasedHardwareResponse(ph)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	
	@GetMapping("/{purchasedHardwareServiceId}")
	public ResponseEntity<PurchasedHardwareResponse> getPurchasedHardwareById(
			@PathVariable("purchasedHardwareServiceId") Long purchasedHardwareServiceId) {
		return new ResponseEntity<>(this.purchasedHardwareMapper.toPurchasedHardwareResponse(purchasedHardwareService.getPurchasedHardwareById(purchasedHardwareServiceId)), HttpStatus.OK);
	}
	
	
	
	@PostMapping
	public ResponseEntity<Void> savePurchasedHardware(@RequestBody PurchasedHardwareRequest purchasedHardwareRequest) {
		purchasedHardwareService.savePurchasedHardware(this.purchasedHardwareMapper.toPurchasedHardware(purchasedHardwareRequest));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{purchasedHardwareId}")
	public ResponseEntity<Void> deletePurchasedHardwareById(@PathVariable("purchasedHardwareId") Long purchasedHardwareId) {
		purchasedHardwareService.deletePurchasedHardwareById(purchasedHardwareId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping("/{purchasedHardwareId}")
	public ResponseEntity<Void> modifyPurchasedHardwareById(@PathVariable("purchasedHardwareId") Long purchasedHardwareId, @RequestBody PurchasedHardwareRequest purchasedHardwareRequest) {
		purchasedHardwareService.modifyPurchasedHardwareById(purchasedHardwareId, this.purchasedHardwareMapper.toPurchasedHardware(purchasedHardwareRequest));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
