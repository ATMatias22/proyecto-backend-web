package com.sensor.exception;

import java.util.Date;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(BlogAppException.class)
	public ResponseEntity<HashMap<String, DetailsError>> blogExceptionHandler(BlogAppException exception, WebRequest webRequest) {
		
		HashMap<String, DetailsError> data = new HashMap<>();

		
		DetailsError errorDetalles = new DetailsError(new Date(), exception.getMessage(),
				webRequest.getDescription(false), exception.getEstado().value());
		
		data.put("error", errorDetalles);

		
		return new ResponseEntity<>(data, exception.getEstado());
	}

		
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<HashMap<String, DetailsError>> blogAppExceptionHandler(BadCredentialsException exception,
			WebRequest webRequest) {
		System.out.println("Estoy aca------------------BadCredentialsException");

		HashMap<String, DetailsError> data = new HashMap<>();

		DetailsError errorDetalles = new DetailsError(new Date(), "Algunas de sus credenciales es incorrecta",
				webRequest.getDescription(false), HttpStatus.UNAUTHORIZED.value());
		
		data.put("error", errorDetalles);
		
		return new ResponseEntity<>(data, HttpStatus.UNAUTHORIZED);
	}
	
	
	
	@ExceptionHandler(JWTException.class)
    public ResponseEntity<HashMap<String, DetailsError>> jwtExceptionHandler(JWTException e, WebRequest webRequest ) {
		
		HashMap<String, DetailsError> data = new HashMap<>();

		
		DetailsError errorDetalles = new DetailsError(new Date(), e.getMessage(),
				webRequest.getDescription(false), HttpStatus.UNAUTHORIZED.value());
		
		data.put("error", errorDetalles);

		return new ResponseEntity<>(data, HttpStatus.UNAUTHORIZED);
    }
	
	
	// tiene q  ver con MyAccessDeniedHandler
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<HashMap<String, DetailsError>> accessDeniedExceptionHandler(AccessDeniedException exception, WebRequest webRequest) {
		
		HashMap<String, DetailsError> data = new HashMap<>();

		DetailsError errorDetalles = new DetailsError(new Date(), "Acceso denegado",
				webRequest.getDescription(false), HttpStatus.FORBIDDEN.value());
		
		data.put("error", errorDetalles);
		
		return new ResponseEntity<>(data, HttpStatus.FORBIDDEN);
	}
	
	// tiene q  ver con MyAccessDeniedHandler
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<HashMap<String, DetailsError>> authenticationExceptionHandler(AuthenticationException exception, WebRequest webRequest) {
		
		HashMap<String, DetailsError> data = new HashMap<>();

		DetailsError errorDetalles = new DetailsError(new Date(), "Algunas de sus credenciales es incorrecta",
				webRequest.getDescription(false), HttpStatus.UNAUTHORIZED.value());
		
		data.put("error", errorDetalles);

		return new ResponseEntity<>(data, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<HashMap<String, DetailsError>> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException  exception, WebRequest webRequest) {
		
		HashMap<String, DetailsError> data = new HashMap<>();


		DetailsError errorDetalles = new DetailsError(new Date(), "Ruta no encontrada",
				webRequest.getDescription(false), HttpStatus.NOT_FOUND.value());
		
		data.put("error", errorDetalles);

		return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<HashMap<String, DetailsError>> exceptionHandler(Exception exception, WebRequest webRequest) {
		
		HashMap<String, DetailsError> data = new HashMap<>();

		DetailsError errorDetalles = new DetailsError(new Date(), exception.getMessage(),
				webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		data.put("error", errorDetalles);

		return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
