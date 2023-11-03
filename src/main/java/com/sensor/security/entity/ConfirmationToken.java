package com.sensor.security.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ConfirmationToken")
public class ConfirmationToken {


    @Id
    @Column(name="ID_ConfirmationToken")
    private String idConfirmationToken;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String token;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "fk_user"
    )
    private User fkUser;

    public ConfirmationToken(String idConfirmationToken,
                             String token,
                             LocalDateTime  createdAt,
                             User user) {
        this.idConfirmationToken = idConfirmationToken;
        this.token = token;
        this.createdAt = createdAt;
        this.fkUser = user;
    }
}
