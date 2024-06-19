package com.jcode_development.bookstore.exceptions.handler;

import com.jcode_development.bookstore.exceptions.ExceptionResponse;
import com.jcode_development.bookstore.exceptions.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleException(Exception exception, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				exception.getMessage(),
				request.getDescription(false)
		);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ExceptionResponse> notFoundExceptionHandler(Exception exception, WebRequest request){
		ExceptionResponse response = new ExceptionResponse(
				new Date(),
				exception.getMessage(),
				request.getDescription(false)
		);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	
}
