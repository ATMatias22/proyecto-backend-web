package com.sensor.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sensor.dto.product.request.ProductDTO;

public interface IProductService {

	void saveProduct(String productDTOJSON, MultipartFile file);

	void deleteProductById(Long productId);

	ProductDTO getProductByName(String name);

	void modifyProductById(Long productId, ProductDTO productDTO);

	List<ProductDTO> getAllEnabledProducts();

	ProductDTO getEnabledProductById(Long productId);
	
	

	

	
}
