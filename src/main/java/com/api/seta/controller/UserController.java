package com.api.seta.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.seta.annotation.CurrentUser;
import com.api.seta.dto.UserResponseDTO;
import com.api.seta.mapper.UserMapper;
import com.api.seta.model.User;
import com.api.seta.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @GetMapping
  public ResponseEntity<UserResponseDTO> getUser(@CurrentUser User user) {
    if (user == null) return ResponseEntity.status(401).build();

    return ResponseEntity.ok(userMapper.toResponseDTO(user));
  }
}
