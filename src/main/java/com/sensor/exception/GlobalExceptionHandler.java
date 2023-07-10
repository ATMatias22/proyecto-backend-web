package com.sensor.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.sensor.exception.dto.DetailsError;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static final String ERROR  = "error";

	@ExceptionHandler(GeneralException.class)
	public ResponseEntity<HashMap<String, DetailsError>> generalExceptionHandler(GeneralException exception, WebRequest webRequest) {

		logger.info("estoy en GeneralException");
		HashMap<String, DetailsError> dataError = new HashMap<>();

		DetailsError detailsError = new DetailsError(new Date(), exception.getMessage(),
				webRequest.getDescription(false), exception.getStatus().value());

		dataError.put(ERROR, detailsError);


		return new ResponseEntity<>(dataError, exception.getStatus());
	}


	@ExceptionHandler(JwtException.class)
	public ResponseEntity<HashMap<String, DetailsError>> jwtExceptionHandler(JwtException e, WebRequest webRequest) {

		logger.info("estoy en JwtException");

		HashMap<String, DetailsError> dataError = new HashMap<>();


		DetailsError detailsError = new DetailsError(new Date(), e.getMessage(),
				webRequest.getDescription(false), HttpStatus.UNAUTHORIZED.value());

		dataError.put(ERROR, detailsError);

		return new ResponseEntity<>(dataError, HttpStatus.UNAUTHORIZED);
	}

	// tiene q  ver con MyAccessDeniedHandler
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<HashMap<String, DetailsError>> accessDeniedExceptionHandler(AccessDeniedException exception, WebRequest webRequest) {

		logger.info("estoy en AccessDeniedException");
		HashMap<String, DetailsError> dataError = new HashMap<>();

		DetailsError detailsError = new DetailsError(new Date(), "Acceso denegado",
				webRequest.getDescription(false), HttpStatus.FORBIDDEN.value());

		dataError.put(ERROR, detailsError);

		return new ResponseEntity<>(dataError, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<HashMap<String, DetailsError>> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException  exception, WebRequest webRequest) {

		logger.info("estoy en MethodArgumentTypeMismatchException");

		HashMap<String, DetailsError> dataError = new HashMap<>();

		DetailsError detailsError = new DetailsError(new Date(), "Recurso no encontrado",
				webRequest.getDescription(false), HttpStatus.NOT_FOUND.value());

		dataError.put(ERROR, detailsError);

		return new ResponseEntity<>(dataError, HttpStatus.NOT_FOUND);
	}

	//
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<HashMap<String, DetailsError>> dataIntegrityViolationException(DataIntegrityViolationException  exception, WebRequest webRequest) {

		logger.info("estoy en DataIntegrityViolationException");

		HashMap<String, DetailsError> dataError = new HashMap<>();

		DetailsError detailsError = new DetailsError(new Date(), "No se pudo realizar la accion",
				webRequest.getDescription(false), HttpStatus.BAD_REQUEST.value());

		dataError.put(ERROR, detailsError);

		return new ResponseEntity<>(dataError, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<HashMap<String, DetailsError>> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException  exception, WebRequest webRequest) {

		logger.info("estoy en HttpRequestMethodNotSupportedException");

		HashMap<String, DetailsError> dataError = new HashMap<>();


		DetailsError detailsError = new DetailsError(new Date(), "Recurso no encontrado",
				webRequest.getDescription(false), HttpStatus.NOT_FOUND.value());

		dataError.put(ERROR, detailsError);

		return new ResponseEntity<>(dataError, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<HashMap<String, DetailsError>> httpMessageNotReadableException(HttpMessageNotReadableException  exception, WebRequest webRequest) {

		logger.info("estoy en HttpMessageNotReadableException");
		logger.info(exception.getMessage());

		HashMap<String, DetailsError> dataError = new HashMap<>();

		DetailsError detailsError = new DetailsError(new Date(), "No se puede guardar este dato",
				webRequest.getDescription(false), HttpStatus.BAD_REQUEST.value());

		dataError.put(ERROR, detailsError);

		return new ResponseEntity<>(dataError, HttpStatus.NOT_FOUND);
	}


	//tiene que ver con las validaciones
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<HashMap<String, DetailsError>> methodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest webRequest) {
		logger.info("estoy en MethodArgumentNotValidException");

		HashMap<String, DetailsError> dataError = new HashMap<>();

		String errors = exception.getBindingResult().getFieldErrors()
				.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(". "));

		DetailsError detailsError = new DetailsError(new Date(), errors,
				webRequest.getDescription(false), HttpStatus.BAD_REQUEST.value());

		dataError.put(ERROR, detailsError);


		return new ResponseEntity<>(dataError, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<HashMap<String, DetailsError>> exceptionHandler(Exception exception, WebRequest webRequest) {

		logger.info("estoy en Exception");


		HashMap<String, DetailsError> dataError = new HashMap<>();

		DetailsError detailsError = new DetailsError(new Date(), exception.getMessage(),
				webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value());

		dataError.put(ERROR, detailsError);

		return new ResponseEntity<>(dataError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
