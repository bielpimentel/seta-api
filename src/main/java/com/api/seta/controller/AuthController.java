package com.api.seta.controller;

import com.api.seta.dto.LoginDTO;
import com.api.seta.dto.LoginResponseDTO;
import com.api.seta.model.User;
import com.api.seta.service.JWTService;
import com.api.seta.service.UserService;

import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

  private final UserService userService;
  private final JWTService jwtService;
  // private final PasswordEncoder passwordEncoder;

  public AuthController(UserService userService, JWTService jwtService) {
    this.userService = userService;
    this.jwtService = jwtService;
    // this.passwordEncoder = passwordEncoder;
  }

  // @PostMapping
  // public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
  //   User user = userService.findByEmail(loginDTO.email());

  //   if (user == null || !passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
  //     return ResponseEntity.status(401).body("Credenciais inválidas");
  //   }

  //   String token = jwtService.generateToken(user);
  //   return ResponseEntity.ok().body(new TokenResponse(token));
  // }

  // static class TokenResponse {
  //   private final String token;

  //   public TokenResponse(String token) {
  //     this.token = token;
  //   }

  //   public String getToken() {
  //     return token;
  //   }
  // }

  @PostMapping
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
    // Aqui normalmente você buscaria o usuário do banco de dados e validaria a senha
    // Por enquanto vamos fazer fixo para exemplo

    if ("biel@fatec.com".equals(loginDTO.email()) && "123".equals(loginDTO.password())) {
      User admin = userService.findByEmail(loginDTO.email());

      String token = jwtService.generateToken(admin);
      return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    return ResponseEntity.status(401).body(new LoginResponseDTO("Credenciais inválidas"));
  }
}
