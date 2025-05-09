package com.api.seta.dto;

import jakarta.validation.constraints.NotBlank;

public record NewAccountRequestPasswordDTO(
  @NotBlank(message = "Campo obrigatório") String password, 
  @NotBlank(message = "Campo obrigatório") String passwordConfirmation
) {}
