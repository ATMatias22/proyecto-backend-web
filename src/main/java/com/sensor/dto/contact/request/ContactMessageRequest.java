package com.sensor.dto.contact.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Calendar;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessageRequest {

	@NotBlank(message = "El nombre no puede ser nulo ni vacio")
	private String name;

	@NotBlank(message = "El apellido no puede ser nulo ni vacio")
	private String lastname;

	@NotBlank(message = "El email no puede ser nulo ni vacio")
	private String email;

	@NotBlank(message = "El mensaje no puede ser nulo ni vacio")
	private String message;

	@NotBlank(message = "La razon del contacto no puede ser nulo ni vacio")
	private String reasonForContact;

}
