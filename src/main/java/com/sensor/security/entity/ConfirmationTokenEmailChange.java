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
@Entity(name = "ConfirmationTokenEmailChange")
public class ConfirmationTokenEmailChange {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="ID_ConfirmationTokenEmailChange")
    private Long idConfirmationTokenEmailChange;

    @Column(nullable = false)
    private String token;

    @Column(name="new_email", length = 150, nullable = false)
    private String newEmail;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Argentina/Buenos_Aires")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "FK_User"
    )
    private User fkUser;

    public ConfirmationTokenEmailChange(
                             String token,
                             LocalDateTime  createdAt,
                             User user,
                                String newEmail
    ) {
        this.token = token;
        this.createdAt = createdAt;
        this.fkUser = user;
        this.newEmail = newEmail;
    }
}
