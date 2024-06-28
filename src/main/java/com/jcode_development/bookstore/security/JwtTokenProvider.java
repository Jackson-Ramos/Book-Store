package com.jcode_development.bookstore.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jcode_development.bookstore.model.user.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;

@Service
public class JwtTokenProvider {
	
	@Value("${security.jwt.token.secret-key:}")
	private String secretKey;
	
	@Value("${security.jwt.token.expire-length:3600000}")
	private Long validateInMilliseconds = 3600000L;
	
	public String generateToken(User user) {
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		Date now = new Date();
		try {
			return JWT.create()
					.withIssuer(issuerUrl)
					.withSubject(user.getUsername())
					.withExpiresAt(new Date(now.getTime() + validateInMilliseconds))
					.sign(algorithm);
			
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Error while generating token", exception);
		}
	}
	
	public String validateToken(String token) {
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		try {
			return JWT.require(algorithm)
					.withIssuer(issuerUrl)
					.build()
					.verify(token)
					.getSubject();
			
		} catch (JWTVerificationException exception) {
			return "";
		}
	}
	
	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring("Bearer ".length());
		}
		return null;
	}
}
