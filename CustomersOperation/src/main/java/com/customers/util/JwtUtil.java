package com.customers.util;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


public class JwtUtil {
	
	private static final Key secretKey = generateSecretKey();// Replace with your own secret key
    private static final Key key = Keys.hmacShaKeyFor(secretKey.getEncoded());
    private static final long expirationMillis = 86400000; // 24 hours

    public static Key generateSecretKey() {
        // Generate a secure random secret key with HMAC SHA-256 algorithm
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
    
    public static String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Invalid token or expired
            return false;
        }
    }

    public static String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
