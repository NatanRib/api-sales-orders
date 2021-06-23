package com.natanribeiro.appvendas.resource.dto.user;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotEmpty;

import com.natanribeiro.appvendas.domain.entity.MyUser;
import com.natanribeiro.appvendas.entity.enums.UserRole;

public class CreateUserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Field 'username' cannot be empty")
	private String username;
	@NotEmpty(message="Field 'password' cannot be empty")
	private String password;
	private String email;
	private String birthdate;
	
	public CreateUserDTO() {}

	public CreateUserDTO(@NotEmpty String username, @NotEmpty String password, String email, String birthdate) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.birthdate = birthdate;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getBithdate() {
		return birthdate;
	}
	
	public MyUser toUser() {
		MyUser user = new MyUser();
		if(birthdate != null) {
			user.setBirthdate(Instant.parse(birthdate));
		}
		user.setUsername(username);
		user.setPasswrod(password);
		user.setEmail(email);
		user.setRole(UserRole.USER);
		return user;
	}
}
