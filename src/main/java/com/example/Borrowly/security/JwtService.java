package com.example.Borrowly.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secret;
    
    @Value("${application.security.jwt.access-token-expiration:3600000}") // 1 hour default
    private long accessTokenExpiration;
    
    @Value("${application.security.jwt.refresh-token-expiration:604800000}") // 7 days default
    private long refreshTokenExpiration;

    public String generateAccessToken(String email){
        return generateToken(email, accessTokenExpiration);
    }
    
    public String generateRefreshToken(String email){
        return generateToken(email, refreshTokenExpiration);
    }
    
    private String generateToken(String email, long expiration){
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
    
    public long getAccessTokenExpiration() {
        return accessTokenExpiration;
    }
    
    public long getRefreshTokenExpiration() {
        return refreshTokenExpiration;
    }

    public boolean validateToken(String token){
        try{
            var claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        }catch (JwtException e){
            return false;
        }
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getEmailFromToken(String token){
        return getClaims(token).getSubject();
    }
}