package com.api.seta.service;

import com.api.seta.model.AccessLog;
import com.api.seta.model.AccessType;
import com.api.seta.model.User;
import com.api.seta.repository.AccessLogRepository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccessLogService {

  private final AccessLogRepository repository;

  public AccessLogService(AccessLogRepository repository) {
    this.repository = repository;
  }

  public Page<AccessLog> findAll(String search, AccessType type, Pageable pageable) {
    if (search != null && !search.isBlank() && type == null) {
      return repository.findByUserNameContainingIgnoreCaseOrUserEmailContainingIgnoreCase(search, search, pageable);
    }

    if ((search == null || search.isBlank()) && type != null) {
      return repository.findByType(type, pageable);
    }

    if (search != null && !search.isBlank() && type != null) {
      return repository.searchByUserNameOrEmailAndType(search, type, pageable);
    }

    return repository.findAll(pageable);
  }

  public boolean hasRecentAccess(User user) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime interval = now.minusMinutes(30);

    return repository.existsByUserIdAndTypeAndAccessDateTimeBetween(
      user.getId(), user.getQrCodeType(), interval, now
    );
  }

  public AccessLog store(AccessLog log) {
    return repository.save(log);
  }
}
