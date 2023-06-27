package com.sensor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sensor.dto.sale.request.SaleDTO;
import com.sensor.persistence.entity.Sale;

@Mapper(componentModel = "spring")
public interface SaleMapper {
	
	
	
	
	@Mappings({ 
		@Mapping(source = "idSale", target = "id"), 
		@Mapping(source = "user.name", target = "namePurchaser"),
		@Mapping(source = "user.lastName", target = "lastNamePurchaser"),
		@Mapping(target = "datePurchase", source="created"),
		@Mapping(source = "product.name", target = "productName"),
		@Mapping(source = "product.price", target = "price"),
		@Mapping(source = "quantity", target = "quantity"),
		@Mapping(source = "userId", target = "userId"),
		@Mapping(source = "user.email", target = "email"),
		@Mapping(source = "productId", target = "productId")
		
	})
	SaleDTO toSaleDTO(Sale sale);
	
	@Mappings({ 
		@Mapping(source = "userId", target = "userId"),
		@Mapping(source = "productId", target = "productId"),
		@Mapping(source = "quantity", target = "quantity"),
		@Mapping(target = "created", ignore = true),
		@Mapping(target = "updated", ignore = true),
		@Mapping(target = "idSale", ignore = true),
		@Mapping(target = "product", ignore = true),
		@Mapping(target = "user", ignore = true)
	})
	Sale toSale(SaleDTO saleDTO);


}
