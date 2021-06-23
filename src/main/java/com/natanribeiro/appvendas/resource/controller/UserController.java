package com.natanribeiro.appvendas.resource.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.natanribeiro.appvendas.resource.dto.user.CreateUserDTO;
import com.natanribeiro.appvendas.resource.dto.user.GetUserDTO;
import com.natanribeiro.appvendas.resource.dto.user.GetUserTokenDTO;
import com.natanribeiro.appvendas.resource.dto.user.UserCredentialsDTO;
import com.natanribeiro.appvendas.security.jwt.JwtTokenService;
import com.natanribeiro.appvendas.service.impl.UserDetailsServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserDetailsServiceImpl userService;
	
	@Autowired
	JwtTokenService tokenService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GetUserDTO create(@RequestBody @Valid CreateUserDTO user) {
		return userService.save(user.toUser());
	}
	
	@PostMapping("/auth")
	public GetUserTokenDTO authentic(@RequestBody @Valid UserCredentialsDTO credentials) {
		UserCredentialsDTO user = userService.AuthenticUser(credentials.toUser());
		String token = tokenService.tokenGenerator(user);
		return new GetUserTokenDTO(token);
	}
}
