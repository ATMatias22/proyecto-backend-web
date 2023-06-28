package com.sensor.security.jwt.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {

	private String token;

	@Builder.Default
	private String bearer = "Bearer";

	public JwtResponse(String token) {
		this.token = token;
	}
}
