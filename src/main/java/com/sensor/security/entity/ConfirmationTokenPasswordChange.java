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
@Entity(name = "ConfirmationTokenPasswordChange")
public class ConfirmationTokenPasswordChange {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="ID_ConfirmationTokenPasswordChange")
    private Long idConfirmationTokenPasswordChange;

    @Column(nullable = false)
    private String token;

    @Column(name="new_password", length = 150, nullable = false)
    private String newPassword;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Argentina/Buenos_Aires")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "FK_User"
    )
    private User fkUser;

    public ConfirmationTokenPasswordChange(
                             String token,
                             LocalDateTime  createdAt,
                             User user,
                                String newPassword
    ) {
        this.token = token;
        this.createdAt = createdAt;
        this.fkUser = user;
        this.newPassword = newPassword;
    }
}
