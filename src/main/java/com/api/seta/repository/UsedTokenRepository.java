package com.api.seta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.seta.model.UsedToken;

public interface UsedTokenRepository extends JpaRepository<UsedToken, Long> {
  boolean existsByToken(String token);
}
