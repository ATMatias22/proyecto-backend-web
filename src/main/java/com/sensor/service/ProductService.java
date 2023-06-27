package com.sensor.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sensor.dto.product.request.ProductDTO;

public interface ProductService {

	void save(String productDTOJSON, MultipartFile file);

	void delete(Long productId);

	ProductDTO getProductByName(String name);

	void modify(Long productId, ProductDTO productDTO);

	List<ProductDTO> getAllEnabled();

	ProductDTO getProductEnabled(Long productId);
	
	

	

	
}
