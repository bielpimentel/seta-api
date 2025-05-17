package com.api.seta.dto;

import java.time.LocalDateTime;

public record UserResponseDTO(String name,
                              String email,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt) {}
