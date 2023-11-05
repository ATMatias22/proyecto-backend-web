package com.sensor.dao;

import com.sensor.entity.Stock;


public interface IStockDao {

    void saveStock(Stock stock);

    void saveManyStock(Iterable<Stock> stocks);

    boolean existsStockWithDeviceCode(String deviceCode);

}
