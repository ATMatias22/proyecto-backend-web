package com.sensor.repository;

import com.sensor.entity.Favorite;
import com.sensor.entity.Product;
import com.sensor.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface IFavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUser(User user);

    @Modifying
    void deleteFirstByUserAndProduct(User user, Product product);

    boolean existsByUserAndProduct(User user, Product product);
}
