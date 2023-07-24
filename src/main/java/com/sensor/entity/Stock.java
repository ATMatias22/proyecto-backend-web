package com.sensor.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name ="Stock")
@Data
public class Stock {

    @Id
    @Column(name = "id_stock")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @OneToOne(mappedBy = "stock")
    private Product product;

}
