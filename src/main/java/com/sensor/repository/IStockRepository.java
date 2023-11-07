package com.sensor.repository;

import com.sensor.entity.Cart;
import com.sensor.entity.Product;
import com.sensor.entity.Stock;
import com.sensor.enums.StockState;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IStockRepository extends JpaRepository<Stock, Long> {


    boolean existsByDeviceCode(String deviceCode);

    int countByStockStateAndCartIsNullAndPlacedOnAPhysicalDeviceIsTrueAndProduct(StockState stockState, Product product);

    List<Stock> findByStockStateAndCartIsNullAndPlacedOnAPhysicalDeviceIsTrueAndProduct(StockState stockState, Product product, Pageable pageable);

    List<Stock> findByStockStateAndPlacedOnAPhysicalDeviceIsTrueAndProductAndCart(StockState stockState, Product product, Cart cart, Pageable pageable);

    List<Stock> findByProduct(Product product);

}
