package com.sensor.dto.sale.response.saleforuser;

import com.sensor.enums.SaleOrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleForUserLoggedInResponse {

    private Long saleOrderId;
    private SaleOrderState state;
    private Double subtotal;
    private Double total;
    private Long cartId;
    private String created;
    private String updated;

    private PaymentMethodInSaleForUserLoggedInResponse paymentMethod;

    private ShippingMethodInSaleForUserLoggedInResponse shippingMethod;

    private List<SaleProductInSaleForUserLoggedInResponse> products;

    private Set<SaleAddressInSaleForUserLoggedInResponse> addresses;

}
