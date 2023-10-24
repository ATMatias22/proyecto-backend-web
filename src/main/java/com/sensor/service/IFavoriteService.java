package com.sensor.service;


import com.sensor.security.entity.User;
import com.sensor.utils.transport.favorite.FavoriteTransportToController;
import com.sensor.utils.transport.favorite.FavoriteTransportToService;

import java.util.List;

public interface IFavoriteService {


    List<FavoriteTransportToController> getAllFavoritesByUserLoggedIn(User userLoggedIn);

    void saveFavorite(FavoriteTransportToService favoriteTransportToService, User userLoggedIn);

    void deleteFirstFavoriteByUserAndProduct(FavoriteTransportToService favoriteTransportToService, User userLoggedIn);

    void deleteAllFavoritesByUser(User userLoggedIn);


}
