package com.sensor.mapper;

import com.sensor.dto.product.request.ProductDTO;
import com.sensor.entity.Product;
import com.sensor.utils.ProductTransport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mappings({
            @Mapping(source = "productTransport.product.productId", target = "id"),
            @Mapping(source = "productTransport.product.name", target = "name"),
            @Mapping(source = "productTransport.product.price", target = "price"),
            @Mapping(source = "productTransport.product.description", target = "description"),
            @Mapping(source = "productTransport.product.userId", target = "idUser"),
            @Mapping(source = "productTransport.product.image", target = "image"),
            @Mapping(source = "productTransport.image", target = "file")
    })
    ProductDTO productTransportToProductDTO(ProductTransport productTransport);


    @Mappings({
            @Mapping(source = "productDTO.name", target = "name"),
            @Mapping(source = "productDTO.description", target = "description"),
            @Mapping(source = "productDTO.price", target = "price"),
            @Mapping(source = "productDTO.idUser", target = "userId"),
            @Mapping(source = "productDTO.image", target = "image"),
            @Mapping(target = "enabled", constant = "true"),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "updated", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "productId", ignore = true)

    })
    Product toProduct(ProductDTO productDTO);


}
