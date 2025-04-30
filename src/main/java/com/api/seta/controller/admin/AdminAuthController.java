package com.api.seta.controller.admin;

import com.api.seta.dto.LoginDTO;
import com.api.seta.dto.LoginResponseDTO;
import com.api.seta.model.Role;
import com.api.seta.model.User;
import com.api.seta.service.JWTService;
import com.api.seta.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login/admin")
public class AdminAuthController {

  private final UserService userService;
  private final JWTService jwtService;
  private final PasswordEncoder passwordEncoder;

  public AdminAuthController(UserService userService, JWTService jwtService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.jwtService = jwtService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {

    User user = userService.findByEmail(loginDTO.email());

    if (user == null || !passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
      return ResponseEntity.status(401).body(new LoginResponseDTO("Credenciais inválidas"));
    }

    if (user.getRole() == Role.USER) {
      return ResponseEntity.status(403).body(new LoginResponseDTO("Acesso restrito a administradores."));
    }

    String token = jwtService.generateToken(user);
    return ResponseEntity.ok(new LoginResponseDTO(token));
  }
}
