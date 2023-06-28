package com.sensor.security.dto.user.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginUserRequest {

    @NotBlank(message = "El email no puede ser nulo ni vacio")
    private String email;

    @NotBlank(message = "El password no puede ser nulo ni vacio")
    private String password;

}
