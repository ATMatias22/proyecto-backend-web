package com.sensor.pattern.sale.strategy;

import com.sensor.dao.ISaleOrderDao;
import com.sensor.entity.SaleOrder;
import com.sensor.enums.SaleOrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleDeliverProductsStateStrategy extends SaleOrderStateStrategy{

    @Autowired
    private ISaleOrderDao saleOrderDao;

    @Override
    public SaleOrderState getState() {
        return SaleOrderState.ENTREGAR_PRODUCTOS;
    }

    @Override
    public void nextStep(SaleOrder saleOrder) {
        saleOrder.setState(SaleOrderState.TERMINADO);
        this.saleOrderDao.saveSaleOrder(saleOrder);
    }

    @Override
    public void cancel(SaleOrder saleOrder) {
        saleOrder.setState(SaleOrderState.CANCELADO);
        this.saleOrderDao.saveSaleOrder(saleOrder);
    }
}
