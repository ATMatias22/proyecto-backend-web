package com.sensor.dto.sale.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

	private Long id;
	private String namePurchaser;
	private String lastNamePurchaser;
	private Date datePurchase;
	private String productName;
	private Long price;
	private Long quantity;
	private Long userId;
	private String email;
	private Long productId;
}
