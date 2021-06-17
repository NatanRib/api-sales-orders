package com.natanribeiro.appvendas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.natanribeiro.appvendas.domain.entity.MyUser;
import com.natanribeiro.appvendas.domain.repository.UserDAO;
import com.natanribeiro.appvendas.resource.dto.user.GetUserDTO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser user = dao.findUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(
						String.format("User with username '%s' not found!", username)));
		
		return User
				.builder()
				.username(user.getUsername())
				.password(user.getPasswrod())
				.roles("USER")
				.build();
	}

	public GetUserDTO save(MyUser user) {
		user.setPasswrod(encoder.encode(user.getPasswrod()));
		return GetUserDTO.FromUser(dao.save(user));
	}

}
