package com.sensor.repository;

import com.sensor.entity.Cart;
import com.sensor.entity.TemporaryCartAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ITemporaryCartAddressRepository extends JpaRepository<TemporaryCartAddress,Long> {

    @Modifying
    @Query("DELETE FROM TemporaryCartAddress cp WHERE cp.cart = :cart")
    void deleteTemporaryCartAddressByCart(@Param("cart") Cart cart);
}
