package com.natanribeiro.appvendas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.natanribeiro.appvendas.domain.entity.MyUser;
import com.natanribeiro.appvendas.domain.repository.UserDAO;
import com.natanribeiro.appvendas.resource.dto.user.GetUserDTO;
import com.natanribeiro.appvendas.resource.dto.user.GetUserTokenDTO;
import com.natanribeiro.appvendas.security.jwt.JwtTokenService;
import com.natanribeiro.appvendas.service.exception.InvalidPasswordException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	JwtTokenService tokenService;
	
	@Override
	public MyUser loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser user = dao.findUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(
						String.format("User with username '%s' not found!", username)));
		return user;
	}

	public MyUser loadUserById(Integer id) throws UsernameNotFoundException {
		MyUser user = dao.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException(
						String.format("User with id '%d' not found!", id)));
		return user;
	}
	
	public GetUserDTO save(MyUser user) {
		user.setPasswrod(encoder.encode(user.getPassword()));
		return GetUserDTO.FromUser(dao.save(user));
	}
	
	public GetUserTokenDTO AuthenticUser(MyUser user) {
		MyUser u = loadUserByUsername(user.getUsername());
		if(encoder.matches(user.getPassword(), u.getPassword())) {
			String token = tokenService.tokenGenerator(u);
			return new GetUserTokenDTO(token);
		}else {
			throw new InvalidPasswordException();
		}
	}
}
