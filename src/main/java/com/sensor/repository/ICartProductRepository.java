package com.sensor.repository;

import com.sensor.entity.Cart;
import com.sensor.entity.CartProduct;
import com.sensor.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICartProductRepository extends JpaRepository<CartProduct, Long> {

    boolean existsByCart(Cart cart);

    Optional<CartProduct> findByProductAndCart(Product product, Cart cart);

}
