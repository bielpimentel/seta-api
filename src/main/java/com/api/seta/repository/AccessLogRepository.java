package com.api.seta.repository;

import com.api.seta.model.AccessLog;
import com.api.seta.model.AccessType;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
  boolean existsByUserIdAndTypeAndAccessDateTimeBetween(
    Long userId,
    AccessType type,
    LocalDateTime start,
    LocalDateTime end
  );
}
