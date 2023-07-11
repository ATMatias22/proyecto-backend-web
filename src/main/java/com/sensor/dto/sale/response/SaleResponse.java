package com.sensor.dto.sale.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponse {

	private Long id;
	private String namePurchaser;
	private String lastNamePurchaser;
	private String datePurchase;
	private String productName;
	private Long price;
	private Long quantity;
	private Long userId;
	private String email;
	private Long productId;
}
