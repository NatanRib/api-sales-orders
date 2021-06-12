package com.natanribeiro.appvendas.resource.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.natanribeiro.appvendas.resource.exception.object.DefaultExceptionResponse;
import com.natanribeiro.appvendas.service.exception.DatabaseException;

@RestControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler(ResponseStatusException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public DefaultExceptionResponse notFound(ResponseStatusException ex, HttpServletRequest req){
		return new DefaultExceptionResponse(ex.getReason(), req.getRequestURI());
	}
	
	@ExceptionHandler(DatabaseException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public DefaultExceptionResponse constraintViolationException(DatabaseException ex,
			HttpServletRequest req) {
		return new DefaultExceptionResponse(ex.getMessage(), req.getRequestURI());
	}
}
