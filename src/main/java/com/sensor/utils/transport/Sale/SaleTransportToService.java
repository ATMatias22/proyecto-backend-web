package com.sensor.utils.transport.Sale;


import com.sensor.entity.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleTransportToService {

    private Long quantity;

    private Long productId;

}