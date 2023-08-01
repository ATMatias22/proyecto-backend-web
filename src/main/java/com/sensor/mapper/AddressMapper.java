package com.sensor.mapper;

import com.sensor.dto.address.request.AddressRequest;
import com.sensor.dto.address.response.AddressResponse;
import com.sensor.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

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


    @Mappings({
            @Mapping(source = "addressRequest.street", target = "street"),
            @Mapping(source = "addressRequest.streetNumber", target = "streetNumber"),
            @Mapping(source = "addressRequest.floor", target = "floor"),
            @Mapping(source = "addressRequest.apartmentNumber", target = "apartmentNumber"),
            @Mapping(source = "addressRequest.typeOfAddress", target = "typeOfAddress"),
            @Mapping(target = "addressId", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "carts", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "updated", ignore = true),
    })
    Address addressRequestToAddress(AddressRequest addressRequest);
    List<Address> addressRequestToAddress(List<AddressRequest> addressRequest);


}
