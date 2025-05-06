package com.api.seta.dto;

import java.time.LocalDateTime;

public record NewAccountRequestAdminResponseDTO(String email, String name, LocalDateTime createdAt) {}
