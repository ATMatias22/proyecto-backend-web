package com.sensor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SaleAddress")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleAddress {

    @Id
    @Column(name = "id_sale_address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleAddressId;

    private String street;

    private String street_number;

    private String floor;

    private String aparmentNumber;

    @Column(name = "name", length = 50, nullable = false)
    private String typeOfAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_sale_order")
    private SaleOrder saleOrder;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updated;






}
