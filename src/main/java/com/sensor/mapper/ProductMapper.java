package com.sensor.mapper;

import com.sensor.dto.product.request.ModifyProductRequest;
import com.sensor.dto.product.request.ProductRequest;
import com.sensor.dto.product.response.ProductResponse;
import com.sensor.entity.Product;
import com.sensor.utils.transport.ProductTransportToController;
import com.sensor.utils.transport.ProductTransportToService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mappings({
            @Mapping(source = "productTransport.product.productId", target = "id"),
            @Mapping(source = "productTransport.product.name", target = "name"),
            @Mapping(source = "productTransport.product.price", target = "price"),
            @Mapping(source = "productTransport.product.description", target = "description"),
            @Mapping(source = "productTransport.fileInBase64", target = "imageBase64"),
    })
    ProductResponse productTransportToControllerToProductProductResponse(ProductTransportToController productTransport);


    @Mappings({
            @Mapping(source = "productRequest", target = "product"),
            @Mapping(source = "file", target = "file"),
    })
    ProductTransportToService productRequestToProductTransportToService(ProductRequest productRequest, MultipartFile file);


    @Mappings({
            @Mapping(source = "productRequest.name", target = "name"),
            @Mapping(source = "productRequest.description", target = "description"),
            @Mapping(source = "productRequest.price", target = "price"),
            @Mapping(target = "productId", ignore = true),
            @Mapping(target = "enabled", constant = "true"),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "updated", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "image", ignore = true),

    })
    Product productRequestToProduct(ProductRequest productRequest);




    @Mappings({
            @Mapping(source = "modifyProductRequest", target = "product"),
            @Mapping(source = "file", target = "file"),
    })
    ProductTransportToService modifyProductRequestToProductTransportToService(ModifyProductRequest modifyProductRequest, MultipartFile file);


    @Mappings({
            @Mapping(source = "modifyProductRequest.name", target = "name"),
            @Mapping(source = "modifyProductRequest.description", target = "description"),
            @Mapping(source = "modifyProductRequest.price", target = "price"),
            @Mapping(target = "productId", ignore = true),
            @Mapping(target = "enabled", constant = "true"),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "updated", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "image", ignore = true),

    })
    Product modifyProductRequestToProduct(ModifyProductRequest modifyProductRequest);


}
