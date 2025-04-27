package com.api.seta.controller;

import com.api.seta.dto.UserDTO;
import com.api.seta.mapper.UserMapper;
import com.api.seta.model.User;
import com.api.seta.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final UserMapper mapper;

  public UserController(UserService userService, UserMapper mapper) {
    this.mapper = mapper;
    this.userService = userService;
  }

  @GetMapping
  public List<User> getAll() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getById(@PathVariable Long id) {
    User user = userService.findById(id);

    if (user == null) return ResponseEntity.notFound().build();

    return ResponseEntity.ok(user);
  }

  @PostMapping
  public ResponseEntity<User> create(@RequestBody UserDTO dto) {
    User user = mapper.toEntity(dto);
    userService.store(user);

    return ResponseEntity.created(null).body(user);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserDTO dto) {
    User existingUser = userService.findById(id);

    if (existingUser == null) return ResponseEntity.notFound().build();

    User mergedUser = mapper.mergeToEntity(existingUser, dto);
    User updatedUser = userService.store(mergedUser);

    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    User user = userService.findById(id);

    if (user == null) return ResponseEntity.notFound().build();

    userService.delete(user);
    return ResponseEntity.noContent().build();
  }
}
