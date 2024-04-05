package com.hr.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {

	Map<String, String> map = new HashMap<String, String>();

	ex.getBindingResult().getAllErrors().forEach(error -> {

	String fn = ((FieldError) error).getField();

	String msg = error.getDefaultMessage();

	map.put(fn, msg);


	});

	return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);



	}

}




