package com.sensor.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name ="Cart_Product")
@Data
public class CartProduct {


    @Id
    @Column(name = "id_cart_product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cart", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_product", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Double quantity;

}
