package com.sensor.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SaleProduct")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleProduct {
    @Id
    @Column(name = "id_sale_product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleProductId;

    @Column(name = "id_product", nullable = false)
    private Long productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name ="description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_sale_order")
    private SaleOrder saleOrder;

    @Column(name = "added_to_cart", nullable = false)
    private LocalDateTime addedToCart;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updated;



}
