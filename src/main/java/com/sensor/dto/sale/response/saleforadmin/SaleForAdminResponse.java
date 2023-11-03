package com.sensor.dto.sale.response.saleforadmin;

import com.sensor.enums.SaleOrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleForAdminResponse {

    private Long saleOrderId;
    private SaleOrderState state;
    private Double subtotal;
    private Double total;
    private Long cartId;
    private String created;
    private String updated;

    private UserInSaleForAdminResponse user;

    private PaymentMethodInSaleForAdminResponse paymentMethod;

    private ShippingMethodInSaleForAdminResponse shippingMethod;

    private List<SaleProductInSaleForAdminResponse> products;

    private Set<SaleAddressInSaleForAdminResponse> addresses;

}
