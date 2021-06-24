package com.natanribeiro.appvendas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.natanribeiro.appvendas.security.jwt.JwtAuthFilter;
import com.natanribeiro.appvendas.security.jwt.JwtTokenService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userService;
	
	@Autowired
	JwtTokenService tokenService;
	
	@Autowired
	FilterChainExceptionHandler filterExceptionHandler;
	
	@Bean
	protected JwtAuthFilter jwtAuthFilter() {
		return new JwtAuthFilter(tokenService, userService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/customers/**")
					.hasRole("USER")
				.antMatchers("/products/**")
					.hasRole("ADMIN")
				.antMatchers("/orders/**")
					.hasRole("USER")
				.antMatchers("/orders/**/*")
					.hasRole("USER")
				.antMatchers(HttpMethod.POST, "/users/**")
					.permitAll()
			.and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilterBefore(filterExceptionHandler, UsernamePasswordAuthenticationFilter.class)
				.addFilterAfter(jwtAuthFilter(), FilterChainExceptionHandler.class);
	}
}
