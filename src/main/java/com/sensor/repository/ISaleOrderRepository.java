package com.sensor.repository;

import com.sensor.entity.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleOrderRepository extends JpaRepository<SaleOrder,Long> {
}
