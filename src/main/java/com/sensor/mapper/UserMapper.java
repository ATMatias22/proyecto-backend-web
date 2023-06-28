package com.sensor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sensor.security.dao.IRoleDao;
import com.sensor.dto.UserDTO;
import com.sensor.security.entity.User;
import com.sensor.security.jwt.dto.JWTAuthResponseDTO;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
	
	
	@Autowired
	public IRoleDao IRoleDao;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Mappings({ 
		@Mapping(source = "userDTO.name", target = "name"),
		@Mapping(source = "userDTO.lastName", target = "lastName"),
		@Mapping(source = "userDTO.email", target = "email"),
		@Mapping(source = "userDTO.country", target = "country"),
		@Mapping(source = "userDTO.datesBirth", target = "dateOfBirth"),
		@Mapping( target = "password", expression="java(passwordEncoder.encode(userDTO.getPassword()))"),
		@Mapping( target = "created", ignore = true),
		@Mapping( target = "updated", ignore = true),
		@Mapping( target = "userId", ignore = true),
		@Mapping( target = "enabled", ignore = true),
		@Mapping( target = "roles", ignore = true),
	})
	public abstract User toUser(UserDTO userDTO);
	
	
	@Mappings({ 
		@Mapping(source = "userId", target = "id"), 
		@Mapping(source = "name", target = "name"),
		@Mapping(source = "lastName", target = "lastName"),
		@Mapping(source = "country", target = "country"),
		@Mapping(source = "email", target = "email"),
		@Mapping(source = "dateOfBirth", target = "datesBirth"),
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
		@Mapping(source = "user.dateOfBirth", target = "datesBirth"),
		@Mapping( target = "jwt", source="jwt"),
		@Mapping( target = "password", ignore = true)
	})
	public abstract UserDTO toUserDTO(User user, JWTAuthResponseDTO jwt);


}
