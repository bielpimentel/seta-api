package com.api.seta.dto;

import java.time.LocalDateTime;

public record SupportResponseDTO(String name, 
                                String email, 
                                String phoneNumber, 
                                String subject, 
                                String message, 
                                LocalDateTime createdAt) {}
