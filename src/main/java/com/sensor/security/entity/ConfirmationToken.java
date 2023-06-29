package com.sensor.security.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Argentina/Buenos_Aires")
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
