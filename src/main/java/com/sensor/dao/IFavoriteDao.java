package com.sensor.dao;


import com.sensor.entity.Favorite;
import com.sensor.entity.Product;
import com.sensor.security.entity.User;

import java.util.List;


public interface IFavoriteDao {

    List<Favorite> getAllFavoritesByUser(User user);

    boolean existsFavoriteByUserAndProduct(User user, Product product);

    void saveFavorite(Favorite favorite);

    void deleteFirstFavoriteByUserAndProduct(User user, Product product);

    void deleteAllFavoritesByUser(User user);
}
