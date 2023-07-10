package com.sensor.dto.contact.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessageRequest {
	
	private String name;
	private String lastname;
	private String email;
	private String message;
	private String reasonForContact;

}
