package com.sensor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sensor.dao.IRoleDao;
import com.sensor.dto.UserDTO;
import com.sensor.entity.User;
import com.sensor.security.JWTAuthResponseDTO;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
	
	
	@Autowired
	public IRoleDao IRoleDao;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Mappings({ 
		@Mapping(source = "name", target = "name"), 
		@Mapping(source = "lastName", target = "lastName"),
		@Mapping(source = "email", target = "email"),
		@Mapping(source = "country", target = "country"),
		@Mapping(source = "datesBirth", target = "datesBirth"),
		@Mapping( target = "password", expression="java(passwordEncoder.encode(userDTO.getPassword()))"),
		@Mapping( target = "roleId", expression="java(IRoleDao.getRoleByName(\"ROLE_USER\").get().getIdRole())"),
		@Mapping( target = "created", ignore = true),
		@Mapping( target = "updated", ignore = true),
		@Mapping( target = "userId", ignore = true),
		@Mapping( target = "role", ignore = true),
	})
	public abstract User toUser(UserDTO userDTO);
	
	
	@Mappings({ 
		@Mapping(source = "userId", target = "id"), 
		@Mapping(source = "name", target = "name"),
		@Mapping(source = "lastName", target = "lastName"),
		@Mapping(source = "country", target = "country"),
		@Mapping(source = "email", target = "email"),
		@Mapping(source = "datesBirth", target = "datesBirth"),
		@Mapping( target = "jwt", ignore = true),
		@Mapping( target = "password", ignore = true)
	})
	public abstract UserDTO toUserDTO(User user);
	
	
	@Mappings({ 
		@Mapping(source = "user.userId", target = "id"), 
		@Mapping(source = "user.name", target = "name"),
		@Mapping(source = "user.lastName", target = "lastName"),
		@Mapping(source = "user.country", target = "country"),
		@Mapping(source = "user.email", target = "email"),
		@Mapping(source = "user.datesBirth", target = "datesBirth"),
		@Mapping( target = "jwt", source="jwt"),
		@Mapping( target = "password", ignore = true)
	})
	public abstract UserDTO toUserDTO(User user, JWTAuthResponseDTO jwt);


}
