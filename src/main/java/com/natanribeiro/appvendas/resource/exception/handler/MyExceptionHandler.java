package com.natanribeiro.appvendas.resource.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.natanribeiro.appvendas.resource.exception.object.DefaultResponseException;
import com.natanribeiro.appvendas.service.exception.BadRequestException;
import com.natanribeiro.appvendas.service.exception.DatabaseException;
import com.natanribeiro.appvendas.service.exception.InvalidPasswordException;
import com.natanribeiro.appvendas.service.exception.RecordNotFoundException;

import io.jsonwebtoken.JwtException;

@RestControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public DefaultResponseException notFound(RecordNotFoundException ex, HttpServletRequest req){
		return new DefaultResponseException(ex.getMessage(), req.getRequestURI());
	}
	
	@ExceptionHandler(DatabaseException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public DefaultResponseException constraintViolationException(DatabaseException ex,
			HttpServletRequest req) {
		return new DefaultResponseException(ex.getMessage(), req.getRequestURI());
	}
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public DefaultResponseException badRequestException(BadRequestException ex,
			HttpServletRequest req) {
		return new DefaultResponseException(ex.getMessage(), req.getRequestURI());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public DefaultResponseException atributeNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest req) {
		return new DefaultResponseException(ex.getAllErrors(), req.getRequestURI());
	}
	
	@ExceptionHandler(InvalidPasswordException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public DefaultResponseException invalidPasswordException(InvalidPasswordException ex,
			HttpServletRequest req) {
		return new DefaultResponseException(ex.getMessage(), req.getRequestURI());
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public DefaultResponseException usernameNotFoundException(UsernameNotFoundException ex,
			HttpServletRequest req) {
		return new DefaultResponseException(ex.getMessage(), req.getRequestURI());
	}
	
	@ExceptionHandler(JwtException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public DefaultResponseException JwtException(JwtException ex,
			HttpServletRequest req) {
		return new DefaultResponseException(ex.getMessage(), req.getRequestURI());
	}
	
	/*
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public DefaultResponseException genericException(RuntimeException ex, 
			HttpServletRequest req) {
		return new DefaultResponseException(ex.getMessage(), req.getRequestURI());
	}
	*/
}
