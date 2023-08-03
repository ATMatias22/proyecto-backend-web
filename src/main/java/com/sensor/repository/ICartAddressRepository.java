package com.sensor.repository;

import com.sensor.entity.CartAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartAddressRepository extends JpaRepository<CartAddress,Long> {
}
