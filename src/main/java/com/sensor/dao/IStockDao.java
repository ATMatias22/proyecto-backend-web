package com.sensor.dao;

import com.sensor.entity.Cart;
import com.sensor.entity.Product;
import com.sensor.entity.Stock;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


public interface IStockDao {

    void saveStock(Stock stock);

    void saveManyStock(Iterable<Stock> stocks);

    boolean existsStockWithDeviceCode(String deviceCode);

    int getAvailableStockQuantityByProduct(Product product);

    List<Stock> getNAvaibleStockQuantityByProduct(Product product, Pageable pageable);

    void saveStockIterable(Iterable<Stock> stocks);

    List<Stock> getNAvaibleStockQuantityByProductAndCart(Product product, Cart cart, Pageable pageable);

    List<Stock> getStockByProduct(Product product);

    Optional<Stock> getStockById(Long stockId);

}
