package com.sensor.service;

import java.util.List;

import com.sensor.utils.transport.product.ProductTransportToController;
import com.sensor.utils.transport.product.ProductTransportToService;

public interface IProductService {

	void saveProduct(ProductTransportToService productTransportToService);

	void deleteProductById(Long productId);

	ProductTransportToController getProductByName(String name);

	void modifyProductById(Long productId, ProductTransportToService productTransportToService);

	List<ProductTransportToController> getAllEnabledProducts();

	ProductTransportToController getEnabledProductById(Long productId);
	
	

	

	
}
