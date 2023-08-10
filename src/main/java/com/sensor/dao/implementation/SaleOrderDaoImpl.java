package com.sensor.dao.implementation;

import com.sensor.dao.ISaleOrderDao;
import com.sensor.entity.SaleOrder;
import com.sensor.enums.SaleOrderState;
import com.sensor.repository.ISaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaleOrderDaoImpl implements ISaleOrderDao {
    @Autowired
    private ISaleOrderRepository saleOrderRepository;
    @Override
    public void saveSaleOrder(SaleOrder saleOrder) {
        this.saleOrderRepository.save(saleOrder);
    }

    @Override
    public List<SaleOrder> getSaleOrderByUserIdAndState(Long userId, SaleOrderState state) {
        return this.saleOrderRepository.findByUserIdAndState(userId, state);
    }
}
