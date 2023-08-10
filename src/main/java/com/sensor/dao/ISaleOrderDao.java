package com.sensor.dao;

import com.sensor.entity.SaleOrder;
import com.sensor.enums.SaleOrderState;

import java.util.List;

public interface ISaleOrderDao {
    void saveSaleOrder(SaleOrder saleOrder);

    List<SaleOrder> getSaleOrderByUserIdAndState(Long userId, SaleOrderState state);

}
