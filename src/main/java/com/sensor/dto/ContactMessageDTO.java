package com.sensor.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessageDTO {
	
	private Long id;
	private String name;
	private String lastName;
	private String email;
	private String message;
	private String reasonForContact;
	private Calendar created;

}
