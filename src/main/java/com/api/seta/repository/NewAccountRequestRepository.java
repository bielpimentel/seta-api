package com.api.seta.repository;

import com.api.seta.model.NewAccountRequest;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewAccountRequestRepository extends JpaRepository<NewAccountRequest, String> {
  boolean existsByEmail(String email);
  Optional<NewAccountRequest> findByEmail(String email);
  Page<NewAccountRequest> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email, Pageable pageable);
}
