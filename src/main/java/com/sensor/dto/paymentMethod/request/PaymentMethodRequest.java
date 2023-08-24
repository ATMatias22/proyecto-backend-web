package com.sensor.dto.paymentMethod.request;

import com.sensor.external.dto.CardPaymentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodRequest {

    private String name;

    private CardPaymentDTO informationCard;

}
