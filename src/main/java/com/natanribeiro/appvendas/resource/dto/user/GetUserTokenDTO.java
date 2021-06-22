package com.natanribeiro.appvendas.resource.dto.user;

import java.io.Serializable;

public class GetUserTokenDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String token;
	
	public GetUserTokenDTO() {}

	public GetUserTokenDTO(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
