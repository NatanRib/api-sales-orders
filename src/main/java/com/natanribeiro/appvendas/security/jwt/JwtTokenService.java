package com.natanribeiro.appvendas.security.jwt;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.natanribeiro.appvendas.domain.entity.MyUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenService {
	
	@Value("${jwt.expiration}")
	private String expiration;
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String tokenGenerator(MyUser user) {
		return Jwts.builder()
				.setSubject(user.getUsername())
				.setExpiration(Date.from(
						Instant.now().plusSeconds( Long.parseLong(expiration) * 60)))
				.signWith(SignatureAlgorithm.HS512, secret)
			.compact();
	}
	
	private Claims getClaims(String token) throws ExpiredJwtException{
		return Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
	}
	
	public boolean isValidToken(String token) throws ExpiredJwtException{
		return Date.from(Instant.now()).before(getClaims(token).getExpiration());
	}
	
	public String getUsername(String token) throws ExpiredJwtException{
		return getClaims(token).getSubject();
	}
}
