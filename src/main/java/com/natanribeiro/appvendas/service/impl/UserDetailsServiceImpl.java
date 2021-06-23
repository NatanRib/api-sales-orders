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
import com.natanribeiro.appvendas.resource.dto.user.UserCredentialsDTO;
import com.natanribeiro.appvendas.service.exception.InvalidPasswordException;

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
				.roles(user.getRole().name())
				.build();
	}

	public UserDetails loadUserById(Integer id) throws UsernameNotFoundException {
		MyUser user = dao.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException(
						String.format("User with id '%d' not found!", id)));
		
		return User
				.builder()
				.username(user.getUsername())
				.password(user.getPasswrod())
				.roles(user.getRole().name())
				.build();
	}
	
	public GetUserDTO save(MyUser user) {
		user.setPasswrod(encoder.encode(user.getPasswrod()));
		return GetUserDTO.FromUser(dao.save(user));
	}
	
	public UserCredentialsDTO AuthenticUser(MyUser user) {
		UserDetails u = loadUserByUsername(user.getUsername());
		if(encoder.matches(user.getPasswrod(), u.getPassword())) {
			UserCredentialsDTO credentials = new UserCredentialsDTO();
			credentials.setUsername(u.getUsername());
			credentials.setRole(u.getAuthorities().toArray()[0].toString());
			return credentials;
		}else {
			System.out.println("password n bateu");
			throw new InvalidPasswordException();
		}
	}
}
