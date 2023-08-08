package com.sensor.repository;

import com.sensor.entity.Cart;
import com.sensor.entity.CartProduct;
import com.sensor.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ICartProductRepository extends JpaRepository<CartProduct, Long> {

    boolean existsByCart(Cart cart);

    Optional<CartProduct> findByProductAndCart(Product product, Cart cart);

    @Modifying
    @Query("DELETE FROM CartProduct cp WHERE cp.cart = :cart")
    void deleteCartProductsByCart(@Param("cart") Cart cart);

}
