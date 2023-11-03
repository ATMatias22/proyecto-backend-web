package com.sensor.entity;

import com.sensor.security.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="Favorite")
@Data
public class Favorite {

    @Id
    @Column(name = "id_favorite")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_product", nullable = false)
    private Product product;


    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime created;

}
