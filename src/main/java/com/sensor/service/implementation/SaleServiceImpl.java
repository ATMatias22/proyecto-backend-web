package com.sensor.service.implementation;

import java.util.List;

import com.sensor.entity.Product;
import com.sensor.security.MainUser;
import com.sensor.security.service.IUserService;
import com.sensor.service.IProductService;
import com.sensor.utils.transport.Sale.SaleTransportToService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sensor.dao.ISaleDao;
import com.sensor.exception.GeneralException;
import com.sensor.entity.Sale;
import com.sensor.security.entity.User;
import com.sensor.service.ISaleService;

@Service
public class SaleServiceImpl implements ISaleService {


    @Autowired
    private IUserService userService;
    @Autowired
    private IProductService productService;
    @Autowired
    private ISaleDao saleDao;

    @Override
    public List<Sale> getAllSales() {
        return saleDao.getAllSales();
    }

    @Override
    public Sale getSaleById(Long saleId) {
        return saleDao.getSaleById(saleId).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontro la venta con el id : " + saleId));
    }

    @Override
    public List<Sale> getAllSalesByUserLoggedIn() {
        User user = this.userService.getUserLoggedInByEmailInToken();

        return saleDao.getAllSalesByUser(user);
    }


    @Override
    public void saveSale(SaleTransportToService saleTransportToService) {
        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByEmail(mu.getUsername());
        Product product = productService.getEnabledProductById(saleTransportToService.getProductId()).getProduct();
        Sale sale = new Sale();
        sale.setQuantity(saleTransportToService.getQuantity());
        sale.setProduct(product);
        sale.setUser(user);
        saleDao.saveSale(sale);
    }

    @Override
    public void deleteSaleById(Long saleId) {
        this.getSaleById(saleId);
        saleDao.deleteSaleById(saleId);
    }


}
