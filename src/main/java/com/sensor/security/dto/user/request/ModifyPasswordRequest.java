package com.sensor.security.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModifyPasswordRequest {


    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{8,}$", message = "La password debe tener un mínimo de 8 caracteres, de los cuales debe haber al menos una mayúscula y un número")
    @NotNull(message = "La password no puede ser nulo")
    private String password;

    @NotNull(message = "La nueva password no puede ser nulo")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{8,}$", message = "La nueva password debe tener un mínimo de 8 caracteres, de los cuales debe haber al menos una mayúscula y un número")
    private String newPassword;

}
