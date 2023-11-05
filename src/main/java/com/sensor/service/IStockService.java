package com.sensor.service;

import com.sensor.entity.Product;
import com.sensor.entity.Stock;
import com.sensor.security.entity.User;

public interface IStockService {

    void saveStock(Stock stock);

    void saveManyStock(int quantity, Product product, User userLoggerIn);
}
