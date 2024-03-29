package com.sensor.dto.product.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

	private Long id;
	private String name;
	private String description;
	private Long price;
	private String imageBase64;

}
