package com.api.seta.repository;

import com.api.seta.model.MailExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailExtensionRepository extends JpaRepository<MailExtension, Long> {
  boolean existsByMailExtension(String mailExtension);
}