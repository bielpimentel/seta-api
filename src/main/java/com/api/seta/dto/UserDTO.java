package com.api.seta.dto;

import com.api.seta.model.Role;

public record UserDTO(String name, String email, String password, Role role) {}