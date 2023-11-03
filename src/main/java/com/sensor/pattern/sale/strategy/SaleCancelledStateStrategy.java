package com.sensor.pattern.sale.strategy;

import com.sensor.entity.SaleOrder;
import com.sensor.enums.SaleOrderState;
import com.sensor.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SaleCancelledStateStrategy extends SaleOrderStateStrategy {

    @Override
    public SaleOrderState getState() {
        return SaleOrderState.CANCELADO;
    }

    @Override
    public void nextStep(SaleOrder saleOrder) {
        throw new GeneralException(HttpStatus.BAD_REQUEST, "No puede realizar este proceso en el estado: " + this.getState());
    }

    @Override
    public void cancel(SaleOrder saleOrder) {
        throw new GeneralException(HttpStatus.BAD_REQUEST, "No puede realizar este proceso en el estado: " + this.getState());
    }
}
