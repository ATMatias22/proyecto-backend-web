package com.sensor.service;

import java.util.List;

import com.sensor.entity.Product;
import com.sensor.entity.Stock;
import com.sensor.security.entity.User;
import com.sensor.utils.file.FileHelper;
import com.sensor.utils.transport.product.ProductTransportToController;
import com.sensor.utils.transport.product.ProductTransportToService;

public interface IProductService {

	void saveProduct(ProductTransportToService productTransportToService, User userLoggedIn);

	void deleteProductById(Long productId);

	ProductTransportToController getProductByName(String name);

	void modifyProductById(Long productId, ProductTransportToService productTransportToService);

	List<ProductTransportToController> getAllEnabledProducts();

	ProductTransportToController getEnabledProductById(Long productId);

	Product getEnabledProductByIdWithoutBase64Image(Long productId);

	void addStockInProduct(Long productId, int quantity, User userLoggerIn);

	List<Stock> getProductStocksByProductId(Long productId);



	

	

	
}
