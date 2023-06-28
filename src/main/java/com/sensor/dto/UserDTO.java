package com.sensor.dto;

import java.util.Calendar;

import com.sensor.security.jwt.dto.JWTAuthResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	

	private Long id;
	private String name;

	private String lastName;
	
	private String email;
	
	private String country;
	
	
	private Calendar datesBirth;
	
	private String password;
	
	private JWTAuthResponseDTO jwt;

	
	

}
