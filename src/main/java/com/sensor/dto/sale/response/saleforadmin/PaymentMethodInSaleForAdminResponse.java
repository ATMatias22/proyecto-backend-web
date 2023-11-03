package com.sensor.dto.sale.response.saleforadmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodInSaleForAdminResponse {

    private String name;

    private Double discount;

}
