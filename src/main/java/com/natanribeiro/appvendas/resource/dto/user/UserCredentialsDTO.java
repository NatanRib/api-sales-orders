package com.natanribeiro.appvendas.resource.dto.user;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.natanribeiro.appvendas.domain.entity.MyUser;

public class UserCredentialsDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Field 'username' cannot be empty")
	private String username;
	@NotEmpty(message="Field 'password' cannot be empty")
	private String password;
	
	UserCredentialsDTO(){}

	public UserCredentialsDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public MyUser toUser(){
		return new MyUser(null, username, null, password, null);
	}
}
