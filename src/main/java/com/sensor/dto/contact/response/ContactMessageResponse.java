package com.sensor.dto.contact.response;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessageResponse {
	
	private Long id;
	private String name;
	private String lastname;
	private String email;
	private String message;
	private String reasonForContact;
	private String created;

}
