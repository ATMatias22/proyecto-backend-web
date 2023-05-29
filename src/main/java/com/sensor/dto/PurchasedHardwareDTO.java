package com.sensor.dto;

import java.util.Calendar;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedHardwareDTO {
	private Long id;
	private String name;
	private Integer quantity;
	private Calendar datePurchase;
	private String provider;
	private Long price;
	private Long userId;
}
