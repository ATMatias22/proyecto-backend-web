package com.sensor.dao.implementation;

import com.sensor.dao.IFavoriteDao;
import com.sensor.entity.Favorite;
import com.sensor.entity.Product;
import com.sensor.repository.IFavoriteRepository;
import com.sensor.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoriteDaoImpl implements IFavoriteDao {

    @Autowired
    private IFavoriteRepository favoriteRepository;

    @Override
    public List<Favorite> getAllFavoritesByUser(User user) {
        return this.favoriteRepository.findByUser(user);
    }

    @Override
    public boolean existsFavoriteByUserAndProduct(User user, Product product) {
        return this.favoriteRepository.existsByUserAndProduct(user,product);
    }

    @Override
    public void saveFavorite(Favorite favorite) {
        this.favoriteRepository.save(favorite);
    }

    @Override
    public void deleteFirstFavoriteByUserAndProduct(User user, Product product) {
        this.favoriteRepository.deleteFirstByUserAndProduct(user,product);
    }
}
