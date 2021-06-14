package com.natanribeiro.appvendas.resource.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.natanribeiro.appvendas.resource.exception.object.DefaultExceptionResponse;
import com.natanribeiro.appvendas.service.exception.BadRequestException;
import com.natanribeiro.appvendas.service.exception.DatabaseException;
import com.natanribeiro.appvendas.service.exception.RecordNotFoundException;

@RestControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public DefaultExceptionResponse notFound(RecordNotFoundException ex, HttpServletRequest req){
		return new DefaultExceptionResponse(ex.getMessage(), req.getRequestURI());
	}
	
	@ExceptionHandler(DatabaseException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public DefaultExceptionResponse constraintViolationException(DatabaseException ex,
			HttpServletRequest req) {
		return new DefaultExceptionResponse(ex.getMessage(), req.getRequestURI());
	}
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public DefaultExceptionResponse badRequestException(BadRequestException ex,
			HttpServletRequest req) {
		return new DefaultExceptionResponse(ex.getMessage(), req.getRequestURI());
	}
}
