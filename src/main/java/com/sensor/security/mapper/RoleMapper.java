package com.sensor.security.mapper;

import com.sensor.security.dto.role.response.RoleResponse;
import com.sensor.security.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(source = "ERole", target = "role")
    RoleResponse rolToRolResponse(Role role);



}
