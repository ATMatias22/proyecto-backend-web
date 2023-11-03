package com.sensor.pattern.sale.strategy;

import com.sensor.dao.ISaleOrderDao;
import com.sensor.entity.SaleOrder;
import com.sensor.enums.SaleOrderState;
import com.sensor.exception.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SaleFinishedStateStrategy extends SaleOrderStateStrategy{

    @Autowired
    private ISaleOrderDao saleOrderDao;

    @Override
    public SaleOrderState getState() {
        return SaleOrderState.TERMINADO;
    }

    @Override
    public void nextStep(SaleOrder saleOrder) {
        throw new GeneralException(HttpStatus.BAD_REQUEST, "No puede realizar este proceso en el estado: " + this.getState());
    }

    @Override
    public void cancel(SaleOrder saleOrder) {
        saleOrder.setState(SaleOrderState.CANCELADO);
        this.saleOrderDao.saveSaleOrder(saleOrder);
    }
}
