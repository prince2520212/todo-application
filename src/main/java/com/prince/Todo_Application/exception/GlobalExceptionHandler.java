package com.prince.Todo_Application.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	  @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	    public ResponseEntity<?> handleValidation(org.springframework.web.bind.MethodArgumentNotValidException ex) {

	        java.util.Map<String, String> errors = new java.util.HashMap<>();

	        ex.getBindingResult().getFieldErrors().forEach(error -> {
	            errors.put(error.getField(), error.getDefaultMessage());
	        });

	        return ResponseEntity.badRequest().body(errors);
	    }
}
