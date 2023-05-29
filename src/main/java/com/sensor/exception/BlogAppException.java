package com.sensor.exception;


import org.springframework.http.HttpStatus;

public class BlogAppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus estado;

	public BlogAppException(HttpStatus estado, String mensaje) {
		super(mensaje);
		this.estado = estado;
	}

	public HttpStatus getEstado() {
		return estado;
	}

	public void setEstado(HttpStatus estado) {
		this.estado = estado;
	}

}