package com.sensor.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

	private Long id;
	
	private String email;
	
	private String comment;
	
	private Long idUser;
	
	private Long idProduct;
	
	private Calendar created;

	private Calendar updated;
	
}
