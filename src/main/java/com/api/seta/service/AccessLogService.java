package com.api.seta.service;

import com.api.seta.model.AccessLog;
import com.api.seta.model.User;
import com.api.seta.repository.AccessLogRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccessLogService {

  private final AccessLogRepository repository;

  public AccessLogService(AccessLogRepository repository) {
    this.repository = repository;
  }

  public List<AccessLog> findAll() {
    return repository.findAll();
  }

  public boolean hasRecentAccess(User user) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime interval = now.minusMinutes(30);

    return repository.existsByUserIdAndTypeAndAccessDateTimeBetween(
      user.getId(), user.getQrCodeType(), interval, now
    );
  }

  public AccessLog save(AccessLog log) {
    return repository.save(log);
  }
}
