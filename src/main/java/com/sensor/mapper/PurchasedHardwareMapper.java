package com.sensor.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sensor.dto.purchasedHardware.request.PurchasedHardwareDTO;
import com.sensor.entity.PurchasedHardware;

@Mapper(componentModel = "spring")
public interface PurchasedHardwareMapper {
	
	
	@Mappings({ 
		@Mapping(source = "idPurchasedHardware", target = "id"), 
		@Mapping(source = "name", target = "name"),
		@Mapping(source = "quantity", target = "quantity"),
		@Mapping(source = "datePurchase", target = "datePurchase"),
		@Mapping(source = "provider", target = "provider"),
		@Mapping(source = "price", target = "price"),
		@Mapping(source = "userId", target = "userId")
	})
	PurchasedHardwareDTO toPurchasedHardwareDTO (PurchasedHardware purchasedHardware);

	
	@InheritInverseConfiguration
	@Mappings({ 
		@Mapping(target = "idPurchasedHardware", ignore = true),
		@Mapping(target = "created", ignore = true),
		@Mapping(target = "updated", ignore = true),
		@Mapping(target = "user", ignore = true)
	})
	PurchasedHardware toPurchasedHardware (PurchasedHardwareDTO purchasedHardwareDTO);
	
	
	@Mappings({ 
		@Mapping(source = "id", target = "idPurchasedHardware"), 
		@Mapping(source = "name", target = "name"), 
		@Mapping(source = "quantity", target = "quantity"), 
		@Mapping(source = "datePurchase", target = "datePurchase"), 
		@Mapping(source = "provider", target = "provider"), 
		@Mapping(source = "price", target = "price"), 
		@Mapping(source = "userId", target = "userId"), 
		@Mapping(target = "updated", expression ="java(java.util.Calendar.getInstance())"),
		@Mapping(target = "created", ignore = true), 
		@Mapping(target = "user", ignore = true)

	})
	PurchasedHardware toPurchasedHardwareModify (PurchasedHardwareDTO purchasedHardwareDTO);
	
	
	
	

}
