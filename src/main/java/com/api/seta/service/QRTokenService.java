package com.api.seta.service;

import com.api.seta.model.AccessType;
import com.api.seta.model.User;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class QRTokenService {

  private SecretKey key;
  @Value("${api.qrcode.token.secret}")
  private String SECRET;
  @Value("${api.qrcode.token.expiration}")
  private long EXPIRATION_TIME;

  @PostConstruct
  public void init() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    this.key = Keys.hmacShaKeyFor(keyBytes);
  }

  public String generateToken(User user) {
    return Jwts.builder()
            .subject(user.getId().toString())
            .claim("email", user.getEmail())
            .claim("name", user.getName())
            .claim("type", user.getQrCodeType().name())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(key)
            .compact();
  }

  public Long getUserIdFromToken(String token) {
    return Long.parseLong(parseClaims(token).getSubject());
  }

  public String getEmailFromToken(String token) {
    return parseClaims(token).get("email", String.class);
  }

  public String getNameFromToken(String token) {
    return parseClaims(token).get("name", String.class);
  }

  public AccessType getAccessTypeFromToken(String token) {
    String type = parseClaims(token).get("type", String.class);
    return AccessType.valueOf(type);
  }

  public boolean isTokenExpired(String token) {
    Date expiration = parseClaims(token).getExpiration();
    return expiration.before(new Date());
  }

  private Claims parseClaims(String token) {
    return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }
}