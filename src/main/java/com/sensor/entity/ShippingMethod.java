package com.sensor.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name ="ShippingMethod")
@Data
public class ShippingMethod {

    @Id
    @Column(name = "id_shipping_method")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingMethodId;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updated;
}
