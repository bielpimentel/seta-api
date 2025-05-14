package com.api.seta.dto;

import java.time.LocalDateTime;

public record UserResponseDTO(String name,
                              String email,
                              String qrCodePath,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt) {}
