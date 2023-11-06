package com.sensor.dao.implementation;

import com.sensor.dao.IStockDao;
import com.sensor.entity.Cart;
import com.sensor.entity.Product;
import com.sensor.entity.Stock;
import com.sensor.enums.StockState;
import com.sensor.repository.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public class StockDaoImpl implements IStockDao {

    @Autowired
    private IStockRepository stockRepository;

    @Override
    public void saveStock(Stock stock) {
        this.stockRepository.save(stock);
    }

    public void saveManyStock(Iterable<Stock> stocks){
        this.stockRepository.saveAll(stocks);
    }

    @Override
    public boolean existsStockWithDeviceCode(String deviceCode) {
        return this.stockRepository.existsByDeviceCode(deviceCode);
    }

    @Override
    public int getAvailableStockQuantityByProduct(Product product) {
        return this.stockRepository.countByStockStateAndCartIsNullAndPlacedOnAPhysicalDeviceIsTrueAndProduct(StockState.DISPONIBLE, product);
    }

    @Override
    public List<Stock> getNAvaibleStockQuantityByProduct(Product product, Pageable pageable) {
        return this.stockRepository.findByStockStateAndCartIsNullAndPlacedOnAPhysicalDeviceIsTrueAndProduct(StockState.DISPONIBLE, product, pageable);
    }

    @Override
    public void saveStockIterable(Iterable<Stock> stocks) {
        this.stockRepository.saveAll(stocks);
    }

    @Override
    public List<Stock> getNAvaibleStockQuantityByProductAndCart(Product product, Cart cart, Pageable pageable) {
        return this.stockRepository.findByStockStateAndPlacedOnAPhysicalDeviceIsTrueAndProductAndCart(StockState.EN_CARRITO, product,cart,pageable);
    }

    @Override
    public List<Stock> getStockByProduct(Product product) {
        return this.stockRepository.findByProduct(product);
    }
}
