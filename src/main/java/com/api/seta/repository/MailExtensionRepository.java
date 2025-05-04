package com.api.seta.repository;

import com.api.seta.model.MailExtension;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailExtensionRepository extends JpaRepository<MailExtension, Long> {
  Optional<MailExtension> findByMailExtension(String mailExtension);
  Page<MailExtension> findByMailExtensionContainingIgnoreCase(String mailExtension, Pageable pageable);
}