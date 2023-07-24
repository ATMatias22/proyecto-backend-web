package com.sensor.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name ="TypeOfAddress")
@Data
public class TypeOfAddress {

    @Id
    @Column(name = "id_type_of_address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeOfAddressId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @OneToMany(mappedBy="typeOfAddress", fetch = FetchType.LAZY)
    List<Address> addresses;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updated;



}
