package com.api.seta.dto;

import java.time.LocalDateTime;

import com.api.seta.model.AccessType;

public record AccessLogAdminDTO(Long id, 
                                String name, 
                                String email, 
                                AccessType type,
                                LocalDateTime accessDateTime) {}
