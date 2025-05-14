package com.api.seta.dto;

import jakarta.validation.constraints.NotBlank;

public record NewAccountRequestConfirmDTO(
  @NotBlank(message = "Campo obrigatório") String email,
  @NotBlank(message = "Campo obrigatório") String password, 
  @NotBlank(message = "Campo obrigatório") String passwordConfirmation
) {}
