package com.sensor.service.implementation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import java.util.stream.Collectors;

import com.sensor.entity.Product;
import com.sensor.security.MainUser;
import com.sensor.security.service.IUserService;
import com.sensor.utils.directory.DirectoryData;
import com.sensor.utils.directory.DirectoryHandler;
import com.sensor.utils.transport.product.ProductTransportToController;
import com.sensor.utils.transport.product.ProductTransportToService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sensor.dao.IProductDao;
import com.sensor.exception.GeneralException;
import com.sensor.utils.file.FileHelper;
import com.sensor.mapper.ProductMapper;
import com.sensor.security.entity.User;
import com.sensor.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductDao productDao;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductTransportToController> getAllEnabledProducts() {
        return productDao.getAllEnabledProducts().stream().map((product) -> new ProductTransportToController(product, FileHelper.filePathToBase64String(DirectoryData.FILE_DIRECTORY_PRODUCT_IMAGES + product.getImage(), DirectoryData.PRODUCT_DEFAULT_IMAGE))).collect(Collectors.toList());
    }

    @Override
    public ProductTransportToController getEnabledProductById(Long productId) {
        Product product = productDao.getEnabledProductById(productId).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el producto: " + productId));

        return new ProductTransportToController(product, FileHelper.filePathToBase64String(DirectoryData.FILE_DIRECTORY_PRODUCT_IMAGES + product.getImage(), DirectoryData.PRODUCT_DEFAULT_IMAGE));
    }

    @Override
    public Product getEnabledProductByIdWithoutBase64Image(Long productId) {
        return productDao.getEnabledProductById(productId).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontro el producto: " + productId));
    }

    @Override
    @Transactional
    public void saveProduct(ProductTransportToService productTransportToService) {

        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());
        Product product = productTransportToService.getProduct();

        boolean existProductWithName = productDao.getProductByName(product.getName()).isPresent();

        if (existProductWithName) {
            throw new GeneralException(HttpStatus.BAD_REQUEST,
                    "Ya existe un producto con nombre : " + product.getName());
        }

        product.setImage(null);
        product.setUser(userLoggedIn);
        Product savedProduct = this.productDao.saveProduct(product);

        if(!productTransportToService.getFile().isEmpty()){
            Long savedProductId = savedProduct.getProductId();
            DirectoryHandler<Long> dh = new DirectoryHandler<>(savedProductId, savedProduct.getImage(),
                    DirectoryData.PREFIX_PRODUCT_DIRECTORY_NAME, productTransportToService.getFile(), DirectoryData.FILE_DIRECTORY_PRODUCT_IMAGES);
            dh.prepareDirectoryForSave();
            savedProduct.setImage(dh.getNewPathForDB());
            this.productDao.saveProduct(savedProduct);
            try {
                dh.ifIsNecessaryCreateOrDeleteThenDoIt();
            } catch (IOException e) {
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Problemas con el sistema de archivos del servidor");
            }
        }
    }

    @Override
    public void deleteProductById(Long productId) {
        this.productDao.getEnabledProductById(productId).ifPresentOrElse
                (product -> {
                    this.productDao.deleteProductById(productId);
                    String path = DirectoryData.FILE_DIRECTORY_PRODUCT_IMAGES
                            + productId;
                    FileHelper.deleteDirectory(new File(path));
                }, () -> {
                    throw new GeneralException(HttpStatus.NOT_FOUND, "No se encontró el producto con id : " + productId);
                });
    }

    @Override
    public ProductTransportToController getProductByName(String name) {
        Product product = productDao.getProductByName(name).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontró el producto: " + name));

        return new ProductTransportToController(product, FileHelper.filePathToBase64String(product.getImage(), DirectoryData.FILE_DIRECTORY_PRODUCT_IMAGES));
    }

    @Override
    public void modifyProductById(Long productId, ProductTransportToService productTransportToService) {

        Product productWithNewData = productTransportToService.getProduct();
        Product productToModify = this.productDao.getEnabledProductById(productId).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontró el producto con id : " + productId));
        boolean existProductWithName = productDao.getProductByName(productWithNewData.getName()).isPresent();
        if (existProductWithName) {
            throw new GeneralException(HttpStatus.CONFLICT,
                    "Ya existe un producto con nombre : " + productWithNewData.getName());
        }

        productToModify.setName(productWithNewData.getName());
        productToModify.setPrice(productWithNewData.getPrice());
        productToModify.setDescription(productWithNewData.getDescription());

        DirectoryHandler<Long> dh = new DirectoryHandler<>(productToModify.getProductId(),
                productToModify.getImage(), DirectoryData.PREFIX_PRODUCT_DIRECTORY_NAME,
                productTransportToService.getFile(), DirectoryData.FILE_DIRECTORY_PRODUCT_IMAGES
        );
        dh.prepareDirectoryForModify();

        productToModify.setImage(dh.getNewPathForDB());
        productDao.saveProduct(productToModify);

        try {
            dh.ifIsNecessaryCreateOrDeleteThenDoIt();
        } catch (IOException e) {
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Problemas con el sistema de archivos del servidor");
        }

    }

}
