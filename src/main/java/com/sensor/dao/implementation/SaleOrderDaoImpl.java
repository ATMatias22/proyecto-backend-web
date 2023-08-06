package com.sensor.dao.implementation;

import com.sensor.dao.ISaleOrderDao;
import com.sensor.entity.SaleOrder;
import com.sensor.repository.ISaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SaleOrderDaoImpl implements ISaleOrderDao {
    @Autowired
    private ISaleOrderRepository saleOrderRepository;
    @Override
    public void saveSaleOrder(SaleOrder saleOrder) {
        this.saleOrderRepository.save(saleOrder);
    }
}
