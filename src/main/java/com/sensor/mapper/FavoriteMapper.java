package com.sensor.mapper;

import com.sensor.dto.favorite.response.FavoriteResponse;
import com.sensor.entity.Favorite;
import com.sensor.utils.date.StringToLocalDateTimeAndViceVersa;
import com.sensor.utils.transport.favorite.FavoriteTransportToController;
import com.sensor.utils.transport.favorite.FavoriteTransportToService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public abstract class FavoriteMapper {

    @Autowired
    protected StringToLocalDateTimeAndViceVersa stdv;

    @Mappings({
            @Mapping(source = "favoriteTransportToController.product", target = "product"),
            @Mapping(target = "created", expression = "java(stdv.getString(favoriteTransportToController.getCreated()))")
    })
    public abstract FavoriteResponse toFavoriteResponse(FavoriteTransportToController favoriteTransportToController);

    @Mappings({
            @Mapping(source = "productId", target = "productId"),
    })
    public abstract FavoriteTransportToService toFavoriteTransportToService(Long productId);

}
