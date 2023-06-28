package com.sensor.security.exception;

import org.springframework.http.HttpStatus;

public class UnabledAccountException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final HttpStatus status;

    public UnabledAccountException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
