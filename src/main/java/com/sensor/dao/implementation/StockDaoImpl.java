package com.sensor.dao.implementation;

import com.sensor.dao.IStockDao;
import com.sensor.entity.Stock;
import com.sensor.repository.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StockDaoImpl implements IStockDao {

    @Autowired
    private IStockRepository stockRepository;

    @Override
    public void saveStock(Stock stock) {
        this.stockRepository.save(stock);
    }
}
