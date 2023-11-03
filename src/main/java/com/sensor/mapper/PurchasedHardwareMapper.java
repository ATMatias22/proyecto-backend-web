package com.sensor.mapper;

import com.sensor.dto.purchasedHardware.request.PurchasedHardwareRequest;
import com.sensor.dto.purchasedHardware.response.PurchasedHardwareResponse;
import com.sensor.utils.date.StringToLocalDateAndViceVersa;
import com.sensor.utils.date.StringToLocalDateTimeAndViceVersa;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sensor.entity.PurchasedHardware;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PurchasedHardwareMapper {

    @Autowired
    protected StringToLocalDateTimeAndViceVersa stdv;
    @Autowired
    protected StringToLocalDateAndViceVersa sld;

    @Mappings({
            @Mapping(source = "idPurchasedHardware", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(target = "datePurchase", expression = "java(sld.getString(purchasedHardware.getDatePurchase()))"),
            @Mapping(source = "provider", target = "provider"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "purchasedHardware.user.email", target = "userEmail"),
            @Mapping(target = "created", expression = "java(stdv.getString(purchasedHardware.getCreated()))"),
            @Mapping(target = "updated", expression = "java(stdv.getString(purchasedHardware.getUpdated()))"),

    })
    public abstract PurchasedHardwareResponse toPurchasedHardwareResponse(PurchasedHardware purchasedHardware);


    @Mappings({
            @Mapping(target = "idPurchasedHardware", ignore = true),
            @Mapping(source = "purchasedHardwareRequest.name", target = "name"),
            @Mapping(source = "purchasedHardwareRequest.quantity", target = "quantity"),
            @Mapping(target = "datePurchase", expression = "java(sld.getLocalDate(purchasedHardwareRequest.getDatePurchase()))"),
            @Mapping(source = "purchasedHardwareRequest.provider", target = "provider"),
            @Mapping(source = "purchasedHardwareRequest.price", target = "price"),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "updated", ignore = true),
            @Mapping(target = "user", ignore = true)
    })
    public abstract PurchasedHardware toPurchasedHardware(PurchasedHardwareRequest purchasedHardwareRequest);


}
