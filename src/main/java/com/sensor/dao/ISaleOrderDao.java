package com.sensor.dao;

import com.sensor.entity.SaleOrder;
import com.sensor.enums.SaleOrderState;

import java.util.List;
import java.util.Optional;

public interface ISaleOrderDao {
    void saveSaleOrder(SaleOrder saleOrder);

    List<SaleOrder> getSaleOrderByUserIdAndState(Long userId, SaleOrderState state);

    List<SaleOrder> getSaleOrderByState(SaleOrderState state);

    Optional<SaleOrder> getSaleOrderById(Long id);

}
