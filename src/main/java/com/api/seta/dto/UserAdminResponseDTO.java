package com.api.seta.dto;

import java.time.LocalDateTime;

import com.api.seta.model.Role;

public record UserAdminResponseDTO(Long id, 
                                  String name,
                                  String email,
                                  Role role,
                                  LocalDateTime createdAt,
                                  LocalDateTime updatedAt) {}
