package com.sensor.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SaleAddress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleAddress {

    @Id
    @Column(name = "id_sale_address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleAddressId;

    @Column(name = "street", nullable = false, length = 150)
    private String street;

    @Column(name = "street_number", nullable = false, length = 20)
    private String streetNumber;

    @Column(name = "floor", length = 20)
    private String floor;

    @Column(name = "apartment_number", length = 20)
    private String apartmentNumber;


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
