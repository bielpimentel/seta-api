package com.api.seta.service;

import org.springframework.stereotype.Service;

import com.api.seta.model.UsedToken;
import com.api.seta.repository.UsedTokenRepository;

@Service
public class UsedTokenService {

  private final UsedTokenRepository usedTokenRepository;

  public UsedTokenService(UsedTokenRepository usedTokenRepository) {
    this.usedTokenRepository = usedTokenRepository;
  }

  public boolean isTokenUsed(String token) {
    return usedTokenRepository.existsByToken(token);
  }

  public void store(String token) {
    UsedToken usedToken = new UsedToken();
    usedToken.setToken(token);
    usedTokenRepository.save(usedToken);
  }
}
