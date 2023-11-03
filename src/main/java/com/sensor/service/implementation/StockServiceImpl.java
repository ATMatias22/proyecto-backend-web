package com.sensor.service.implementation;

import com.sensor.dao.IStockDao;
import com.sensor.entity.Stock;
import com.sensor.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements IStockService {

    @Autowired
    private IStockDao stockDao;

    @Override
    public void saveStock(Stock stock) {
        this.stockDao.saveStock(stock);
    }

}
