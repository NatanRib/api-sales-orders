package com.natanribeiro.appvendas.security.jwt;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

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

	public String tokenGenerator(MyUser u) {
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("sub", u.getUsername());
		claims.put("userId", u.getId());

		return Jwts.builder().addClaims(claims)
				.setExpiration(Date.from(Instant.now().plusSeconds(Long.parseLong(expiration) * 60)))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private Claims getClaims(String token){
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
	}

	public boolean isValidToken(String token) {
		return Date.from(Instant.now()).before(getClaims(token).getExpiration());
	}

	public String getUsername(String token) throws ExpiredJwtException {
		return getClaims(token).getSubject();
	}
	
	public String getUserId(String token){
		return String.valueOf(getClaims(token).get("userId"));
	}
}
