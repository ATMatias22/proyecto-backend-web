package com.sensor.service;

import com.sensor.entity.SaleOrder;
import com.sensor.enums.SaleOrderState;

import java.util.List;

public interface ISaleOrderService {

    void saveSaleOrder(SaleOrder saleOrder);

    List<SaleOrder> getSaleOrderByUserLoggedInAndState(SaleOrderState state);

}
