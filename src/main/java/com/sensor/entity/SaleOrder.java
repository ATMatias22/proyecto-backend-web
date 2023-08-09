package com.sensor.entity;

import com.sensor.enums.CartState;
import com.sensor.enums.SaleOrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SaleOrder")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrder {

    @Id
    @Column(name = "id_sale_order")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleOrderId;

    @Column(name = "id_user", nullable = false)
    private Long userId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "lastname", length = 50, nullable = false)
    private String lastname;

    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @Column(name = "payment_method_name", nullable = false, length = 150)
    private String paymentMethodName;

    @Column(name = "payment_method_discount", nullable = false)
    private Double paymentMethodDiscount;

    @Column(name = "shipping_method_name", nullable = false, length = 150)
    private String shippingMethodName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state", nullable = false, length = 50)
    private SaleOrderState state;

    @OneToMany(mappedBy="saleOrder", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private Set<SaleAddress> addresses;

    @OneToMany(mappedBy="saleOrder", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private List<SaleProduct> products;

    @Column(name = "cart", nullable = false)
    private Long cart;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updated;



}
