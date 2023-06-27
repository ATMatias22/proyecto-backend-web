package com.sensor.dto.product.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Long id;
	private String name;
	private String description;
	private Long price;
	private Long idUser;
	private String image;
	private String file;
	
}
