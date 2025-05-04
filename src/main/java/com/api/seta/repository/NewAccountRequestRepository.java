package com.api.seta.repository;

import com.api.seta.model.NewAccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewAccountRequestRepository extends JpaRepository<NewAccountRequest, String> {
  boolean existsByEmail(String email);
}
