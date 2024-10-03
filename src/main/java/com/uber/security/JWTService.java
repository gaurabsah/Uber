package com.uber.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.uber.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	@Value("${jwt.secretKey}")
	private String jwtSecretKey;

	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
	}

	public String generateAccessToken(User user) {
		return Jwts.builder().subject(user.getId().toString()).claim("email", user.getEmail())
				.claim("roles", user.getRoles().toString()).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10)).signWith(getSecretKey()).compact();
	}

	public String generateRefreshToken(User user) {
		return Jwts.builder().subject(user.getId().toString()).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30 * 6))
				.signWith(getSecretKey()).compact();
	}

	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
		return Long.valueOf(claims.getSubject());
	}

}