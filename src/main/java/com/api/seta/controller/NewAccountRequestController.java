package com.api.seta.controller;

import com.api.seta.dto.NewAccountRequestDTO;
import com.api.seta.dto.NewAccountRequestConfirmDTO;
import com.api.seta.dto.UserDTO;
import com.api.seta.mapper.UserMapper;
import com.api.seta.model.NewAccountRequest;
import com.api.seta.model.Role;
import com.api.seta.model.User;
import com.api.seta.service.EmailService;
import com.api.seta.service.NewAccountRequestService;
import com.api.seta.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/new-account")
public class NewAccountRequestController {

  private final NewAccountRequestService service;
  private final EmailService emailService;
  private final UserService userService;
  private final UserMapper userMapper;

  public NewAccountRequestController(
      NewAccountRequestService service, 
      EmailService emailService, 
      UserService userService,
      UserMapper userMapper
  ) {
    this.emailService = emailService;
    this.userService = userService;
    this.userMapper = userMapper;
    this.service = service;
  }

  @PostMapping("/{token}")
  public ResponseEntity<User> confirmNewAccount(
      @PathVariable String token, 
      @RequestBody @Valid NewAccountRequestConfirmDTO dto
  ) {
    NewAccountRequest request = service.findByEmailAndToken(dto.email(), token);
    
    if (request == null) return ResponseEntity.badRequest().build();
    if (!dto.password().equals(dto.passwordConfirmation())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "As senhas informadas não conferem");
    }

    UserDTO userDTO = new UserDTO(request.getName(), dto.email(), dto.password(), Role.USER);
    User savedUser = userService.store(userMapper.toEntity(userDTO));
    service.delete(request);

    return ResponseEntity.ok(savedUser);
  }

  @PostMapping
  public ResponseEntity<NewAccountRequest> requestNewAccount(@RequestBody NewAccountRequestDTO dto) {
    User user = userService.findByEmail(dto.email());
    if (user != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credenciais inválidas");

    NewAccountRequest newAccount = service.create(dto);
    if (newAccount == null) return ResponseEntity.badRequest().body(null);

    service.store(newAccount);
    emailService.newAccountMail(newAccount);

    return ResponseEntity.created(null).body(newAccount);
  }
}