package com.example.callerapp.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // Load the secret key from application.properties
    @Value("${jwt.secret}")
    private String secret;

    // Generate a signing key using HMAC SHA-256
    private Key getSigningKey() {
        if (secret == null || secret.length() < 32) {
            throw new IllegalArgumentException("JWT secret key must be at least 256 bits (32 characters)");
        }
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Generate a JWT token with 2 hours expiration
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2)) // 2 hours
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username from the token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validate token: check signature, username match, and expiration
    public boolean validateToken(String token, String username) {
        try {
            String extracted = extractUsername(token);
            return extracted.equals(username) && !isTokenExpired(token);
        } catch (JwtException e) {
            return false;
        }
    }

    // Check if the token is expired
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}