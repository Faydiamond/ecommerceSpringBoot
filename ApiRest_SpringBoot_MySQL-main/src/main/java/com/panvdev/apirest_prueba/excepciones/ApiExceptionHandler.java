package com.panvdev.apirest_prueba.excepciones;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	 @ExceptionHandler(value = {ApiRequestException.class})
	    public ResponseEntity<Object> handleApiRequestException(ApiRequestException ex) {
	       
	        ApiException apiException = new ApiException(
	            ex.getMessage(),
	            ex.getHttpStatus(),
	            null // Puedes dejar en null si no deseas enviar la traza
	        );
	        return new ResponseEntity<>(apiException, ex.getHttpStatus());
	    }
}
