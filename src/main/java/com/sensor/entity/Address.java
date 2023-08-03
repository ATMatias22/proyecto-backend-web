package com.sensor.entity;


import com.sensor.security.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="Address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {


    @Id
    @Column(name = "id_address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "street", nullable = false, length = 150)
    private String street;

    @Column(name = "street_number", nullable = false, length = 20)
    private String streetNumber;

    @Column(name = "floor", length = 20)
    private String floor;

    @Column(name = "apartment_number", length = 20)
    private String apartmentNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_type_of_address", nullable = false)
    private TypeOfAddress typeOfAddress;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updated;


}
