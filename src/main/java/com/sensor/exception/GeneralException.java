package com.sensor.exception;

import org.springframework.http.HttpStatus;

public class GeneralException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private final HttpStatus status;

    public GeneralException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
