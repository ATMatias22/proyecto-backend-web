package com.sensor.exception;


public class JWTException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public JWTException(String mensaje) {
		super(mensaje);
	}

}
