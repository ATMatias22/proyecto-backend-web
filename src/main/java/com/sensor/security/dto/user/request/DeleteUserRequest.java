package com.sensor.security.dto.user.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteUserRequest {

    @NotBlank(message = "La contraseña no puede ser nulo ni vacio")
    private String password;

}
