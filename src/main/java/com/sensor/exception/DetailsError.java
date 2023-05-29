package com.sensor.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class DetailsError {
	
	private Date time;
	private String message;
	private String details;
	private boolean error = true;
	private int httpStatus;
	
	public DetailsError(Date time, String message, String details, int httpStatus) {
		super();
		this.time = time;
		this.message = message;
		this.details = details;
		this.httpStatus = httpStatus;
	}
	
	


}
