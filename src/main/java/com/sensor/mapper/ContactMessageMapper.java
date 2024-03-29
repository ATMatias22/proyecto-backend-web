package com.sensor.mapper;

import com.sensor.dto.contact.request.ContactMessageRequest;
import com.sensor.dto.contact.response.ContactMessageResponse;
import com.sensor.utils.date.StringToLocalDateTimeAndViceVersa;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sensor.entity.ContactMessage;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class ContactMessageMapper {
    @Autowired
    protected StringToLocalDateTimeAndViceVersa stdv;

    @Mappings({
            @Mapping(source = "contactMessage.contactId", target = "id"),
            @Mapping(source = "contactMessage.name", target = "name"),
            @Mapping(source = "contactMessage.lastname", target = "lastname"),
            @Mapping(source = "contactMessage.email", target = "email"),
            @Mapping(source = "contactMessage.reasonForContact", target = "reasonForContact"),
            @Mapping(source = "contactMessage.message", target = "message"),
            @Mapping(target = "created", expression = "java(stdv.getString(contactMessage.getCreated()))")
    })
    public abstract ContactMessageResponse toContactMessageResponse(ContactMessage contactMessage);

    @Mappings({
            @Mapping(source = "contactMessageRequest.name", target = "name"),
            @Mapping(source = "contactMessageRequest.lastname", target = "lastname"),
            @Mapping(source = "contactMessageRequest.email", target = "email"),
            @Mapping(source = "contactMessageRequest.reasonForContact", target = "reasonForContact"),
            @Mapping(source = "contactMessageRequest.message", target = "message"),
            @Mapping(target = "contactId", ignore = true),
            @Mapping(target = "updated", ignore = true),
            @Mapping(target = "created", ignore = true)
    })
    public abstract ContactMessage toContactMessage(ContactMessageRequest contactMessageRequest);


}
