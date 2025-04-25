package com.api.seta.dto;

import com.api.seta.model.AccessType;
import java.time.LocalDateTime;

public record AccessLogDTO(Long userId, AccessType type, LocalDateTime accessDateTime) {}
