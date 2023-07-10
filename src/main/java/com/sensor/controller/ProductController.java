package com.sensor.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensor.dto.product.request.ModifyProductRequest;
import com.sensor.dto.product.request.ProductRequest;
import com.sensor.dto.product.response.ProductResponse;
import com.sensor.exception.GeneralException;
import com.sensor.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sensor.service.IProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	private IProductService productService;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ObjectMapper objectMapper;



	@GetMapping("/all")
	public ResponseEntity<List<ProductResponse>> getAllEnabledProducts() {
		return new ResponseEntity<>(productService.getAllEnabledProducts().stream().map(productTransport -> this.productMapper.productTransportToControllerToProductProductResponse(productTransport)).collect(Collectors.toList()), HttpStatus.OK);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductResponse> getEnabledProductById(@PathVariable("productId") Long productId) {
		return new ResponseEntity<>(this.productMapper.productTransportToControllerToProductProductResponse(productService.getEnabledProductById(productId)), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Void> saveProduct(@RequestPart("product") @Valid ProductRequest productRequest, @RequestPart("file") MultipartFile file) {
		productService.saveProduct(this.productMapper.productRequestToProductTransportToService(productRequest,file));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{productId}")
	public ResponseEntity<Void> deleteProductById(@PathVariable("productId") Long productId) {
		productService.deleteProductById(productId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping("/{productId}")
	public ResponseEntity<Void> modifyProductById(@PathVariable("productId") Long productId, @RequestPart("product") @Valid ModifyProductRequest modifyProductRequest, @RequestPart("file") MultipartFile file) {
		productService.modifyProductById(productId, this.productMapper.modifyProductRequestToProductTransportToService(modifyProductRequest,file));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
