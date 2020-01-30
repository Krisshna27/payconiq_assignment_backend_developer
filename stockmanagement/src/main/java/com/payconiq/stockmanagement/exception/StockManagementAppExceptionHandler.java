package com.payconiq.stockmanagement.exception;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StockManagementAppExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Custom exception handler to handle errors messages in a generic way.
	 */

	 @Override
	 protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
			 HttpHeaders headers, HttpStatus status, WebRequest request) {
		 
	        return new ResponseEntity<>(StockManagementErrorResponse.builder()
	    	        .timestamp( OffsetDateTime.now( ZoneOffset.UTC))
	    	        .errorCode(HttpStatus.BAD_REQUEST.value())
	    	        .error(HttpStatus.BAD_REQUEST.toString())
	    	        .errorDescription(ex.getBindingResult()
	    	                .getFieldErrors()
	    	                .stream()
	    	                .map(x -> x.getDefaultMessage())
	    	                .collect(Collectors.toList()))
	    	        .build(), headers, status);
	 }
	 
	 @ExceptionHandler(ConstraintViolationException.class)
	  public void constraintViolationException(HttpServletResponse response) throws IOException {
	        response.sendError(HttpStatus.BAD_REQUEST.value());
	    }
	 
	 
	 @ExceptionHandler(StockNotFoundAppException.class)
	 public ResponseEntity<StockManagementErrorResponse> handleNotFound(Exception ex, WebRequest request) {	       

	        return new ResponseEntity<>( StockManagementErrorResponse.builder()
	    	        .timestamp( OffsetDateTime.now( ZoneOffset.UTC))
	    	        .errorCode(HttpStatus.NOT_FOUND.value())
	    	        .error(HttpStatus.NOT_FOUND.toString())
	    	        .errorDescription(Arrays.asList(ex.getMessage()))
	    	        .build(), HttpStatus.NOT_FOUND);
	 }
}
