package com.sensor.service;

import com.sensor.entity.Product;
import com.sensor.entity.Stock;
import com.sensor.security.entity.User;

import java.util.List;

public interface IStockService {

    void saveStock(Stock stock);

    void saveManyStock(int quantity, Product product, User userLoggerIn);

    int getAvailableStockQuantityByProduct(Product product);

    List<Stock> getNAvailableStockQuantityByProduct(Product product, int quantity);

    void saveStockIterable(Iterable<Stock> stocks);
}
