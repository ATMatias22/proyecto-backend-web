package com.sensor.dto.purchasedHardware.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedHardwareResponse {
    private Long id;
    private String name;
    private Integer quantity;
    private String datePurchase;
    private String provider;
    private Long price;
    private String userEmail;
    private String created;
    private String updated;
}
