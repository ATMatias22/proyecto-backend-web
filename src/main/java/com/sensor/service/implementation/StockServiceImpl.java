package com.sensor.service.implementation;

import com.sensor.dao.IStockDao;
import com.sensor.entity.Cart;
import com.sensor.entity.Product;
import com.sensor.entity.Stock;
import com.sensor.enums.StockState;
import com.sensor.security.entity.User;
import com.sensor.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StockServiceImpl implements IStockService {

    @Autowired
    private IStockDao stockDao;

    @Override
    public void saveStock(Stock stock) {
        this.stockDao.saveStock(stock);
    }


    @Override
    @Transactional
    public void saveManyStock(int quantity, Product product, User userLoggerIn) {
        List<Stock> stocks = new ArrayList<>();
        Random random = new SecureRandom();

        for (int i = 0; i < quantity; i++) {
            Stock stock = new Stock();
            stock.setProduct(product);
            stock.setPlacedOnAPhysicalDevice(false);
            stock.setStockState(StockState.DISPONIBLE);
            String devicePassword = generateRandomPassword(random);
            String deviceCode;
            do {
                deviceCode = generateRandomCode(random);
            } while (this.stockDao.existsStockWithDeviceCode(deviceCode));
            stock.setDeviceCode(deviceCode);
            stock.setDevicePassword(devicePassword);
            stocks.add(stock);
        }

        this.stockDao.saveManyStock(stocks);
    }

    @Override
    public int getAvailableStockQuantityByProduct(Product product) {
        return this.stockDao.getAvailableStockQuantityByProduct(product);
    }

    @Override
    public List<Stock> getNAvailableStockQuantityByProduct(Product product, int quantity) {

        Pageable pageable = PageRequest.of(0, quantity );
        return this.stockDao.getNAvaibleStockQuantityByProduct(product, pageable);
    }

    @Override
    public void saveStockIterable(Iterable<Stock> stocks) {
        this.stockDao.saveStockIterable(stocks);
    }

    @Override
    public List<Stock> getNAvailableStockQuantityByProductAndCart(Product product, Cart cart, int quantity) {
        Pageable pageable = PageRequest.of(0, quantity );
        return this.stockDao.getNAvaibleStockQuantityByProductAndCart(product, cart, pageable);
    }

    @Override
    public List<Stock> getStocksByProduct(Product product) {
        return this.stockDao.getStockByProduct(product);
    }

    private String generateRandomCode(Random random) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return generateRandomString(characters, 8, random);
    }

    private String generateRandomPassword(Random random) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return generateRandomString(characters, 8, random);
    }

    private String generateRandomString(String characters, int length, Random random) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }


}
