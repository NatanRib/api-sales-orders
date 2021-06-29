package com.natanribeiro.appvendas.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

@Order(2)
public class JwtAuthFilter extends OncePerRequestFilter{

	private JwtTokenService tokenService;
	
	private UserDetailsService userService;
	
	public JwtAuthFilter(JwtTokenService tokenService, UserDetailsService userService) {
		super();
		this.tokenService = tokenService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorization = request.getHeader("Authorization");
		if(authorization != null && authorization.startsWith("Bearer")) {
			String token = authorization.split(" ")[1];			
			if(tokenService.isValidToken(token)) {
					AddAuthUserOnContext(token, request);
			}
		}
		filterChain.doFilter(request, response);
 	}
	
	private void AddAuthUserOnContext(String token, HttpServletRequest request) {
		String username= tokenService.getUsername(token);
		UserDetails user = userService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken authUser = 
				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		String userId = tokenService.getUserId(token);
		request.setAttribute("userId", userId); //testar
		SecurityContextHolder.getContext().setAuthentication(authUser);
	}

}
