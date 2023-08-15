package com.sensor.pattern.sale.strategy;


import com.sensor.entity.SaleOrder;
import com.sensor.enums.SaleOrderState;

public abstract class SaleOrderStateStrategy {


    public abstract SaleOrderState getState();
    public abstract  void nextStep(SaleOrder saleOrder);
    public abstract void cancel(SaleOrder saleOrder);

}
