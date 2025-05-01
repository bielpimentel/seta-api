package com.api.seta.controller.admin;

import com.api.seta.dto.UserDTO;
import com.api.seta.mapper.UserMapper;
import com.api.seta.model.User;
import com.api.seta.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/users")
public class AdminUserController {

  private final UserService userService;
  private final UserMapper mapper;

  public AdminUserController(UserService userService, UserMapper mapper) {
    this.mapper = mapper;
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<Page<User>> getAll(
      @RequestParam(required = false) String search,
      @PageableDefault(size = 10, sort = "id") Pageable pageable
  ) {
    Page<User> users = userService.findAll(search, pageable);

    return ResponseEntity.ok(users);
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
