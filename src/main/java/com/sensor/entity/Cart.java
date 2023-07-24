package com.sensor.entity;


import com.sensor.enums.CartState;
import com.sensor.security.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="Cart")
@Data
public class Cart {

    @Id
    @Column(name = "id_cart")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state", nullable = false, length = 50, unique = true)
    private CartState state = CartState.ESTADO_INICIAL;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_payment_method")
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_shipping_method")
    private ShippingMethod shippingMethod;

    @ManyToMany(mappedBy = "carts", fetch = FetchType.EAGER)
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy="cart", fetch = FetchType.EAGER)
    private List<CartProduct> cartsProducts;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updated;



}
