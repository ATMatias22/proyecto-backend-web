package com.sensor.security.jwt.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

	private String token;

	private String bearer = "Bearer";

	public JwtResponse(String token) {
		this.token = token;
	}
}
