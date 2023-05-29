package com.sensor.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sensor.dto.ContactMessageDTO;
import com.sensor.persistence.entity.ContactMessage;


@Mapper(componentModel = "spring")
public interface ContactMessageMapper {

	
	@Mappings({ 
		@Mapping(source = "contactId", target = "id"), 
		@Mapping(source = "name", target = "name"),
		@Mapping(source = "lastName", target = "lastName"),
		@Mapping(source = "email", target = "email"),
		@Mapping(source = "reasonForContact", target = "reasonForContact"),
		@Mapping(source = "message", target = "message"),
		@Mapping(source = "created", target = "created")
	})
	ContactMessageDTO toContactMessageDTO (ContactMessage contactMessage); 

	@InheritInverseConfiguration
	@Mappings({ 
		@Mapping(target = "contactId", ignore = true),
		@Mapping(target = "created", ignore = true)
	})
	ContactMessage toContactMessage(ContactMessageDTO contactMessageDTO);

	
}
