package com.sensor.web.rest;

import java.util.List;

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

import com.sensor.dto.ProductDTO;
import com.sensor.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	private ProductService productService;


	@GetMapping("/all")
	public ResponseEntity<List<ProductDTO>> getAll() {
		return new ResponseEntity<>(productService.getAllEnabled(), HttpStatus.OK);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable("productId") Long productId) {
		return new ResponseEntity<ProductDTO>(productService.getProductEnabled(productId), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity save(@RequestPart("product") String product, @RequestPart("file") MultipartFile file) {
		productService.save(product,file);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{productId}")
	public ResponseEntity delete(@PathVariable("productId") Long productId) {
		productService.delete(productId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping("/{productId}")
	public ResponseEntity modify(@PathVariable("productId") Long productId, @RequestBody ProductDTO productDTO) {
		productService.modify(productId, productDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
