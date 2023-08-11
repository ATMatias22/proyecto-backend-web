package com.sensor.dto.sale.response.saleforadmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInSaleForAdminResponse {

    private Long userId;

    private String name;

    private String lastname;

    private String email;
}
