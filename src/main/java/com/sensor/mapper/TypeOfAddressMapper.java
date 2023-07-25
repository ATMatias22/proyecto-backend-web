package com.sensor.mapper;

import com.sensor.dto.typeOfAddress.response.TypeOfAddressResponse;
import com.sensor.entity.TypeOfAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TypeOfAddressMapper {

    @Mappings({
            @Mapping(source = "name", target = "name"),
    })
    TypeOfAddressResponse toTypeOfAddress(TypeOfAddress typeOfAddress);

}
