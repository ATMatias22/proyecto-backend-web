package com.sensor.mapper;

import com.sensor.dto.typeOfAddress.request.TypeOfAddressRequest;
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


    @Mappings({
            @Mapping(source = "typeOfAddressRequest.name", target = "name"),
            @Mapping(target = "typeOfAddressId", ignore = true),
            @Mapping(target = "addresses", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "updated", ignore = true),
    })
    TypeOfAddress typeOfAddressRequestToTypeOfAddress(TypeOfAddressRequest typeOfAddressRequest);

}
