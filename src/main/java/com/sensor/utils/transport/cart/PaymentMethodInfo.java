package com.sensor.utils.transport.cart;

import com.sensor.entity.PaymentMethod;
import com.sensor.external.dto.CardPaymentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodInfo {

    private PaymentMethod paymentMethod;

    private CardPaymentDTO paymentInformation;

}
