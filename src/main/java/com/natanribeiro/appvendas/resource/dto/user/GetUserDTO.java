package com.natanribeiro.appvendas.resource.dto.user;

import java.io.Serializable;

import com.natanribeiro.appvendas.domain.entity.MyUser;

public class GetUserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String username;
	private String email;
	private String birthdate;
	
	public GetUserDTO() {}

	public GetUserDTO(Integer id, String username, String email, String birthdate) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.birthdate = birthdate;
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getBirthdate() {
		return birthdate;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public static GetUserDTO FromUser(MyUser user) {
		GetUserDTO u = new GetUserDTO();
		u.setUsername(user.getUsername());
		u.setEmail(user.getEmail());
		u.setId(user.getId());
		if (user.getBirthdate() != null) {
			u.setBirthdate(null);
		}
		return u;
	}
}
