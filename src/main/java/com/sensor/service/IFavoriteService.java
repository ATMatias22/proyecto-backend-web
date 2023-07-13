package com.sensor.service;


import com.sensor.utils.transport.favorite.FavoriteTransportToController;
import com.sensor.utils.transport.favorite.FavoriteTransportToService;

import java.util.List;

public interface IFavoriteService {


    List<FavoriteTransportToController> getAllFavoritesByUserLoggedIn();

    void saveFavorite(FavoriteTransportToService favoriteTransportToService);

    void deleteFirstFavoriteByUserAndProduct(FavoriteTransportToService favoriteTransportToService);


}
