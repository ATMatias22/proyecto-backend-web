package com.sensor.dto.product.response;


import com.sensor.enums.StockState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponse {

    private Long stockId;

    private Long userId;

    private Long cartId;

    private String deviceCode;

    private String devicePassword;

    private Boolean placedOnAPhysicalDevice;

    private StockState stockState;

    private String created;

    private String updated;


}
