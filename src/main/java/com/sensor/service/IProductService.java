package com.sensor.service;

import java.util.List;

import com.sensor.dto.product.request.ProductDTO;
import com.sensor.utils.ProductTransport;
import org.springframework.web.multipart.MultipartFile;

public interface IProductService {

	void saveProduct(String productDTOJSON, MultipartFile file);

	void deleteProductById(Long productId);

	ProductTransport getProductByName(String name);

	void modifyProductById(Long productId, ProductDTO productDTO);

	List<ProductTransport> getAllEnabledProducts();

	ProductTransport getEnabledProductById(Long productId);
	
	

	

	
}
