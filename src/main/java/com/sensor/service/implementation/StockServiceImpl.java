package com.sensor.service.implementation;

import com.sensor.dao.IStockDao;
import com.sensor.entity.Cart;
import com.sensor.entity.Product;
import com.sensor.entity.Stock;
import com.sensor.enums.StockState;
import com.sensor.exception.GeneralException;
import com.sensor.external.web_admin.dto.request.AddDeviceFromWebAdminRequest;
import com.sensor.external.web_admin.jwt.AppMovilJwtProvider;
import com.sensor.security.entity.User;
import com.sensor.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StockServiceImpl implements IStockService {

    @Autowired
    private IStockDao stockDao;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.movil.url.save-product}")
    private String urlSaveProduct;
    @Autowired
    private AppMovilJwtProvider appMovilJwtProvider;

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
            stock.setUser(userLoggerIn);
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

        Pageable pageable = PageRequest.of(0, quantity);
        return this.stockDao.getNAvaibleStockQuantityByProduct(product, pageable);
    }

    @Override
    public void saveStockIterable(Iterable<Stock> stocks) {
        this.stockDao.saveStockIterable(stocks);
    }

    @Override
    public List<Stock> getNAvailableStockQuantityByProductAndCart(Product product, Cart cart, int quantity) {
        Pageable pageable = PageRequest.of(0, quantity);
        return this.stockDao.getNAvaibleStockQuantityByProductAndCart(product, cart, pageable);
    }

    @Override
    public List<Stock> getStocksByProduct(Product product) {
        return this.stockDao.getStockByProduct(product);
    }

    @Override
    @Transactional
    public void changePlacedOnAPhysicalDeviceInStock(Long stockId) {
        Stock stock = this.stockDao.getStockById(stockId).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el stock con este id: " + stockId));
        if (stock.getPlacedOnAPhysicalDevice()) {
            throw new GeneralException(HttpStatus.BAD_REQUEST, "No se puede cambiar porque ya fue cambiado");
        }
        stock.setPlacedOnAPhysicalDevice(true);
        this.stockDao.saveStock(stock);


        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Backend-Token", this.appMovilJwtProvider.generateToken());
        HttpEntity<AddDeviceFromWebAdminRequest> requestEntityDeviceStatus = new HttpEntity<>(new AddDeviceFromWebAdminRequest(stock.getDeviceCode(),stock.getDevicePassword()),headers);
        System.out.println(urlSaveProduct);
        try {
            restTemplate.exchange(urlSaveProduct, HttpMethod.POST, requestEntityDeviceStatus, new ParameterizedTypeReference<Void>() {
            });

        } catch (HttpClientErrorException enf) {
            System.out.println(enf.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo comunicar con el servicio de movil");
        } catch (Exception ex ){
            System.out.println(ex.getMessage());
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo comunicar con el servicio de movil");
        }


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
