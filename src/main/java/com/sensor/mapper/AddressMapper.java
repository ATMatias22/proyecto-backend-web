package com.sensor.mapper;

import com.sensor.dto.address.response.AddressResponse;
import com.sensor.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {TypeOfAddressMapper.class})
public interface AddressMapper {


    @Mappings({
            @Mapping(source = "street", target = "street"),
            @Mapping(source = "streetNumber", target = "streetNumber"),
            @Mapping(source = "floor", target = "floor"),
            @Mapping(source = "apartmentNumber", target = "apartmentNumber"),
            @Mapping(source = "typeOfAddress", target = "typeOfAddress")
    })
    AddressResponse toAddressResponse(Address address);

}
