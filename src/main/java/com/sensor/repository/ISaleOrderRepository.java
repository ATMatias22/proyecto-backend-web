package com.sensor.repository;

import com.sensor.entity.SaleOrder;
import com.sensor.enums.SaleOrderState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISaleOrderRepository extends JpaRepository<SaleOrder,Long> {

    List<SaleOrder> findByUserIdAndState(Long cartId, SaleOrderState state);

    List<SaleOrder> findByState(SaleOrderState state);



}
