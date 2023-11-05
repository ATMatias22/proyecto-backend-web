package com.sensor.dao;

import com.sensor.entity.Product;
import com.sensor.entity.Stock;

import java.awt.print.Pageable;
import java.util.List;


public interface IStockDao {

    void saveStock(Stock stock);

    void saveManyStock(Iterable<Stock> stocks);

    boolean existsStockWithDeviceCode(String deviceCode);

    int getAvailableStockQuantityByProduct(Product product);

    List<Stock> getNAvaibleStockQuantityByProduct(Product product, Pageable pageable);

    void saveStockIterable(Iterable<Stock> stocks);

}
