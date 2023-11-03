package com.sensor.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="PaymentMethod")
@Data
public class PaymentMethod {

    @Id
    @Column(name = "id_payment_method")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentMethodId;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updated;

}
