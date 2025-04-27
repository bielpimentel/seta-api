package com.api.seta.service;

import com.api.seta.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTService {

  private SecretKey key;
  @Value("${api.security.token.secret}")
  private String SECRET;
  @Value("${api.security.token.expiration}")
  private long EXPIRATION_TIME;

  @PostConstruct
  public void init() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    this.key = Keys.hmacShaKeyFor(keyBytes);
  }

  public String generateToken(User user) {
    return Jwts.builder()
              .subject(user.getId().toString())
              .claim("role", user.getRole())
              .issuedAt(new Date())
              .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
              .signWith(key)
              .compact();
  }

  public String getUserIdFromToken(String token) {
    Jws<Claims> claims = Jwts.parser()
                            .verifyWith(key)
                            .build()
                            .parseSignedClaims(token);

    return claims.getPayload().getSubject();
  }

  public String getRoleFromToken(String token) {
    Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

    return claims.get("role", String.class);
  }
}
