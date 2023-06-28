package com.sensor.security.dto.user.request;

import com.sensor.utils.date.validdate.ValidDate;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class NewUserRequest {

	@Size(max = 50, message = "el nombre debe tener como maximo 50 caracteres")
	@NotBlank(message = "El nombre no puede ser nulo ni vacio")
	private String name;


	@Size(max = 50, message = "el apellido debe tener como maximo 50 caracteres")
	@NotBlank(message = "El apellido no puede ser nulo ni vacio")
	private String lastname;

	@NotBlank(message = "El email no puede ser nulo ni vacio")
	private String email;

	@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{8,}$", message = " La password debe tener un mínimo de 8 caracteres, de los cuales debe haber al menos una mayúscula y un número")
	@NotNull(message = "El email no puede ser nulo")
	private String password;

	@NotBlank(message = "La nacionalidad no puede ser nulo ni vacio")
	private String country;

	@ValidDate(message =  "Fecha de nacimiento mal colocada")
	private String dateOfBirth;
	
}
