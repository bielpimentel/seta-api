package com.api.seta.dto;

import com.api.seta.model.AccessType;

import java.time.LocalDateTime;

public record AccessLogDTO(UserResponseDTO user, 
                          AccessType type, 
                          LocalDateTime accessDateTime,
                          String message) {}
