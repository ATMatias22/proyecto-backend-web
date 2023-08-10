package com.sensor.service.implementation;

import com.sensor.dao.ISaleOrderDao;
import com.sensor.entity.SaleOrder;
import com.sensor.enums.SaleOrderState;
import com.sensor.security.MainUser;
import com.sensor.service.ISaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleOrderServiceImpl implements ISaleOrderService {
    @Autowired
    private ISaleOrderDao saleOrderDao;

    @Override
    public void saveSaleOrder(SaleOrder saleOrder) {
        this.saleOrderDao.saveSaleOrder(saleOrder);
    }

    @Override
    public List<SaleOrder> getSaleOrderByUserLoggedInAndState(SaleOrderState state) {

        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return this.saleOrderDao.getSaleOrderByUserIdAndState(mu.getId(), state );
    }
}
