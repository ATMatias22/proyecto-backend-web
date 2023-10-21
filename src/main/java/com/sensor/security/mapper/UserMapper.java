package com.sensor.security.mapper;

import com.sensor.security.dto.user.request.LoginUserRequest;
import com.sensor.security.dto.user.request.ModifyDataRequest;
import com.sensor.security.dto.user.request.NewUserRequest;
import com.sensor.security.dto.user.response.RegisteredUserResponse;
import com.sensor.security.entity.User;
import com.sensor.utils.date.StringToLocalDateAndViceVersa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected StringToLocalDateAndViceVersa stdv;

    @Mappings({
            @Mapping(source = "newUser.name", target = "name"),
            @Mapping(source = "newUser.lastname", target = "lastname"),
            @Mapping(source = "newUser.email", target = "email"),
            @Mapping(source = "newUser.password", target = "password"),
            @Mapping(source = "newUser.country", target = "country"),
            @Mapping(target = "dateOfBirth", expression = "java(stdv.getLocalDate(newUser.getDateOfBirth()))" ),
            @Mapping(target = "userId", ignore = true ),
            @Mapping(target = "roles", ignore = true ),
            @Mapping(target = "created", ignore = true ),
            @Mapping(target = "updated", ignore = true ),
            @Mapping(target = "enabled", ignore = true ),
            @Mapping(target = "address", ignore = true ),
            @Mapping(target = "carts", ignore = true )
    })
    public abstract User newUserRequestToUserEntity(NewUserRequest newUser);

    @Mappings({
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(target = "userId", ignore = true ),
            @Mapping(target = "name", ignore = true ),
            @Mapping(target = "lastname", ignore = true ),
            @Mapping(target = "country", ignore = true ),
            @Mapping(target = "roles", ignore = true ),
            @Mapping(target = "dateOfBirth", ignore = true ),
            @Mapping(target = "created", ignore = true ),
            @Mapping(target = "updated", ignore = true ),
            @Mapping(target = "enabled", ignore = true ),
            @Mapping(target = "address", ignore = true ),
            @Mapping(target = "carts", ignore = true )
    })
    public abstract User loginUserRequestToUserEntity(LoginUserRequest loginUser);

    @Mappings({
            @Mapping(source = "user.userId", target = "id"),
            @Mapping(source = "user.name", target = "name"),
            @Mapping(source = "user.lastname", target = "lastname"),
            @Mapping(source = "user.email", target = "email"),
            @Mapping(source = "user.country", target = "country"),
            @Mapping(target = "datesBirth", expression = "java(stdv.getString(user.getDateOfBirth()))" ),
            @Mapping(source = "user.created", target = "created"),
            @Mapping(source = "user.updated", target = "updated"),
    })
    public abstract RegisteredUserResponse userEntityToRegisteredUserResponse(User user);


    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastname", target = "lastname"),
            @Mapping(source = "email", target = "email"),
            @Mapping(target = "dateOfBirth", expression = "java(stdv.getLocalDate(modifyDataRequest.getDateOfBirth()))" ),
            @Mapping(source = "country", target = "country"),
            @Mapping(target = "password", ignore = true ),
            @Mapping(target = "userId", ignore = true ),
            @Mapping(target = "roles", ignore = true ),
            @Mapping(target = "created", ignore = true ),
            @Mapping(target = "updated", ignore = true ),
            @Mapping(target = "enabled", ignore = true ),
            @Mapping(target = "address", ignore = true ),
            @Mapping(target = "carts", ignore = true )
    })
    public abstract User modifyDataRequestToUserEntity(ModifyDataRequest modifyDataRequest);



}
