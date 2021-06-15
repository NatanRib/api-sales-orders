package com.natanribeiro.appvendas.resource.exception.object;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DefaultResponseException implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private Instant moment;
	private String path;
	private List<String> errors = new ArrayList<>();
	
	public DefaultResponseException() {}

	public DefaultResponseException(String mensage, String path) {
		super();
		this.moment = Instant.now();
		this.errors.add(mensage);
		this.path = path;
	}
	
	public DefaultResponseException(List<ObjectError> errors, String path){
		this.moment = Instant.now();
		this.errors = errors.stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
		this.path = path;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public List<String> getErros() {
		return errors;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
