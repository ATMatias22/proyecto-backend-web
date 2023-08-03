package com.sensor.entity;

import com.sensor.enums.CartState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="Cart_Address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartAddress {

    @Id
    @Column(name = "id_cart_address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartAddressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cart", nullable = false)
    private Cart cart;

    @Column(name = "street", nullable = false, length = 150)
    private String street;

    @Column(name = "street_number", nullable = false, length = 20)
    private String streetNumber;

    @Column(name = "floor", length = 20)
    private String floor;

    @Column(name = "apartment_number", length = 20)
    private String apartmentNumber;

    @Column(name = "type_of_address", nullable = false, length = 100)
    private String typeOfAddress;

}
