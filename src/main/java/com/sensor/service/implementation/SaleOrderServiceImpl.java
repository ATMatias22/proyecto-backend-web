package com.sensor.service.implementation;

import com.sensor.dao.ISaleOrderDao;
import com.sensor.entity.SaleOrder;
import com.sensor.enums.CartState;
import com.sensor.enums.SaleOrderState;
import com.sensor.exception.GeneralException;
import com.sensor.pattern.cart.strategy.CartStateStrategy;
import com.sensor.pattern.sale.strategy.SaleOrderStateStrategy;
import com.sensor.pattern.sale.strategy.SaleOrderStateStrategyFactory;
import com.sensor.security.MainUser;
import com.sensor.service.ISaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleOrderServiceImpl implements ISaleOrderService {
    @Autowired
    private ISaleOrderDao saleOrderDao;

    @Autowired
    private SaleOrderStateStrategyFactory saleOrderStateStrategyFactory;

    @Override
    public void saveSaleOrder(SaleOrder saleOrder) {
        this.saleOrderDao.saveSaleOrder(saleOrder);
    }

    @Override
    public List<SaleOrder> getSaleOrderByUserLoggedInAndState(SaleOrderState state) {

        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return this.saleOrderDao.getSaleOrderByUserIdAndState(mu.getId(), state );
    }

    @Override
    public List<SaleOrder> getSaleOrderByState(SaleOrderState state) {
        return this.saleOrderDao.getSaleOrderByState(state);
    }

    @Override
    public void nextStep(Long saleId) {

        SaleOrder saleOrder = this.saleOrderDao.getSaleOrderById(saleId).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro una venta con id: "+ saleId));
        SaleOrderState state = saleOrder.getState();
        SaleOrderStateStrategy strategy = saleOrderStateStrategyFactory.getStrategy(state);
        strategy.nextStep(saleOrder);

    }

    @Override
    public void cancelSaleOrder(Long saleId) {
        SaleOrder saleOrder = this.saleOrderDao.getSaleOrderById(saleId).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro una venta con id: "+ saleId));
        SaleOrderState state = saleOrder.getState();
        SaleOrderStateStrategy strategy = saleOrderStateStrategyFactory.getStrategy(state);
        strategy.cancel(saleOrder);
    }
}
