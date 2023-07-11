package com.sensor.mapper;

import com.sensor.dto.sale.request.SaleRequest;
import com.sensor.dto.sale.response.SaleResponse;
import com.sensor.utils.transport.Sale.SaleTransportToService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sensor.entity.Sale;

@Mapper(componentModel = "spring")
public interface SaleMapper {
	

	@Mappings({ 
		@Mapping(source = "sale.idSale", target = "id"),
		@Mapping(source = "sale.user.name", target = "namePurchaser"),
		@Mapping(source = "sale.user.lastname", target = "lastNamePurchaser"),
		@Mapping(source	= "sale.created", target = "datePurchase"),
		@Mapping(source = "sale.product.name", target = "productName"),
		@Mapping(source = "sale.product.price", target = "price"),
		@Mapping(source = "sale.quantity", target = "quantity"),
		@Mapping(source = "sale.user.userId", target = "userId"),
		@Mapping(source = "sale.user.email", target = "email"),
		@Mapping(source = "sale.product.productId", target = "productId")
		
	})
	SaleResponse toSaleResponse(Sale sale);
	
	@Mappings({
			@Mapping(source = "saleRequest.productId", target = "productId"),
			@Mapping(source = "saleRequest.quantity", target = "quantity"),
	})
	SaleTransportToService toSaleTransportToService(SaleRequest saleRequest);

}
