package com.sensor.service.implementation;

import com.sensor.dao.ISaleOrderDao;
import com.sensor.entity.SaleOrder;
import com.sensor.service.ISaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class SaleOrderServiceImpl implements ISaleOrderService {
    @Autowired
    private ISaleOrderDao saleOrderDao;

    @Override
    public void saveSaleOrder(SaleOrder saleOrder) {
        this.saleOrderDao.saveSaleOrder(saleOrder);
    }
}
