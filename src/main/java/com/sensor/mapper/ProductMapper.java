package com.sensor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sensor.dto.product.request.ProductDTO;
import com.sensor.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mappings({
            @Mapping(source = "product.productId", target = "id"),
            @Mapping(source = "product.name", target = "name"),
            @Mapping(source = "product.price", target = "price"),
            @Mapping(source = "product.description", target = "description"),
            @Mapping(source = "product.userId", target = "idUser"),
            @Mapping(source = "product.image", target = "image"),
            @Mapping(source = "file", target = "file")
    })
    ProductDTO toProductDTO(Product product, String file);

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
