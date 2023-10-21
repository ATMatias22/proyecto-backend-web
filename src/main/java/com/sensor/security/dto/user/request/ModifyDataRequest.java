package com.sensor.security.dto.user.request;

import com.sensor.utils.date.validdate.ValidDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModifyDataRequest {

    @Size(max = 50, message = "El nombre debe tener como maximo 50 caracteres")
    @NotBlank(message = "El nombre no puede ser nulo ni vacio")
    private String name;

    @Size(max = 50, message = "El apellido debe tener como maximo 50 caracteres")
    @NotBlank(message = "El apellido no puede ser nulo ni vacio")
    private String lastname;

    @NotBlank(message = "El email no puede ser nulo ni vacio")
    private String email;

    @NotBlank(message = "El pais no puede ser nulo ni vacio")
    private String country;

    @ValidDate(message =  "Fecha de nacimiento mal colocada")
    private String dateOfBirth;


}
