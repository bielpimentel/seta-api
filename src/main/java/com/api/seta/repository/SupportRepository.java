package com.api.seta.repository;

import com.api.seta.model.Support;
import com.api.seta.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRepository extends JpaRepository<Support, Long> {
  Page<Support> findByUserNameContainingIgnoreCaseOrUserEmailContainingIgnoreCase(String name, String email, Pageable pageable);
  Page<Support> findByUser(User user, Pageable pageable);
}
