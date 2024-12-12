package com.panvdev.apirest_prueba.excepciones;

import org.springframework.http.HttpStatus;

public class ApiRequestException extends RuntimeException {
	private HttpStatus httpStatus;

    public ApiRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ApiRequestException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public Throwable getCause() {
        return null;  
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return new StackTraceElement[0];  
    }
}
