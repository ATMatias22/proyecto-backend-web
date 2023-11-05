package com.sensor.entity;


import com.sensor.enums.StockState;
import com.sensor.security.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="Stock")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @Column(name = "id_stock")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_product", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    @Column(name = "device_code", nullable = false)
    private String deviceCode;

    @Column(name = "device_password", nullable = false)
    private String devicePassword;

    @Column(name = "placed_on_a_physical_device", nullable = false)
    private Boolean placedOnAPhysicalDevice;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "stock_state", nullable = false, length = 50)
    private StockState stockState;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updated;

}
