package com.sensor.mapper;

import com.sensor.dto.product.request.ModifyProductRequest;
import com.sensor.dto.product.request.ProductRequest;
import com.sensor.dto.product.response.ProductResponse;
import com.sensor.dto.product.response.StockResponse;
import com.sensor.entity.Product;
import com.sensor.entity.Stock;
import com.sensor.utils.date.StringToLocalDateTimeAndViceVersa;
import com.sensor.utils.transport.product.ProductTransportToController;
import com.sensor.utils.transport.product.ProductTransportToService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    protected StringToLocalDateTimeAndViceVersa stdv;


    @Mappings({
            @Mapping(source = "productTransport.product.productId", target = "id"),
            @Mapping(source = "productTransport.product.name", target = "name"),
            @Mapping(source = "productTransport.product.price", target = "price"),
            @Mapping(source = "productTransport.product.description", target = "description"),
            @Mapping(source = "productTransport.fileInBase64", target = "imageBase64"),
    })
    public abstract ProductResponse productTransportToControllerToProductProductResponse(ProductTransportToController productTransport);


    @Mappings({
            @Mapping(source = "productRequest", target = "product"),
            @Mapping(source = "file", target = "file"),
    })
    public abstract ProductTransportToService productRequestToProductTransportToService(ProductRequest productRequest, MultipartFile file);


    @Mappings({
            @Mapping(source = "productRequest.name", target = "name"),
            @Mapping(source = "productRequest.description", target = "description"),
            @Mapping(source = "productRequest.price", target = "price"),
            @Mapping(target = "productId", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "enabled", constant = "true"),
            @Mapping(target = "image", ignore = true),
            @Mapping(target = "cartsProducts", ignore = true),
            @Mapping(target = "stocks", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "updated", ignore = true),

    })
    public abstract Product productRequestToProduct(ProductRequest productRequest);




    @Mappings({
            @Mapping(source = "modifyProductRequest", target = "product"),
            @Mapping(source = "file", target = "file"),
            @Mapping(source = "modifyProductRequest.keepImage", target = "keepImage"),
    })
    public abstract ProductTransportToService modifyProductRequestToProductTransportToService(ModifyProductRequest modifyProductRequest, MultipartFile file);


    @Mappings({
            @Mapping(source = "modifyProductRequest.name", target = "name"),
            @Mapping(source = "modifyProductRequest.description", target = "description"),
            @Mapping(source = "modifyProductRequest.price", target = "price"),
            @Mapping(target = "productId", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "enabled", constant = "true"),
            @Mapping(target = "image", ignore = true),
            @Mapping(target = "cartsProducts", ignore = true),
            @Mapping(target = "stocks", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "updated", ignore = true),

    })
    public abstract Product modifyProductRequestToProduct(ModifyProductRequest modifyProductRequest);


    @Mappings({
            @Mapping(source = "stock.stockId", target = "stockId"),
            @Mapping(source = "stock.user.userId", target = "userId"),
            @Mapping(source = "stock.cart.cartId", target = "cartId"),
            @Mapping(source = "stock.deviceCode", target = "deviceCode"),
            @Mapping(source = "stock.devicePassword", target = "devicePassword"),
            @Mapping(source = "stock.placedOnAPhysicalDevice", target = "placedOnAPhysicalDevice"),
            @Mapping(source = "stock.stockState", target = "stockState"),
            @Mapping(target = "created", expression = "java(stdv.getString(stock.getCreated()))"),
            @Mapping(target = "updated", expression = "java(stdv.getString(stock.getUpdated()))"),
    })
    public abstract StockResponse stockToStockResponse(Stock stock);
    public abstract List<StockResponse> stockToStockResponse(List<Stock> stock);

}
