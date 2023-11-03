package com.sensor.security.dto.user.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConfirmChangeUserEmailRequest {

    @NotBlank(message = "El token no puede ser nulo ni vacio")
    private String token;
}
