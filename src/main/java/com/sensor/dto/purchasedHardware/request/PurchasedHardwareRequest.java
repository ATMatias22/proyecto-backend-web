package com.sensor.dto.purchasedHardware.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedHardwareRequest {

    private String name;
    private Integer quantity;
    private String datePurchase;
    private String provider;
    private Long price;

}
