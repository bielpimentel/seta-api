package com.api.seta.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.api.seta.dto.UserDTO;
import com.api.seta.dto.UserResponseDTO;
import com.api.seta.model.Role;
import com.api.seta.model.User;

@Component
public class UserMapper {

  private final PasswordEncoder passwordEncoder;

  public UserMapper(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public User toEntity(UserDTO dto) {
    if (dto == null) return null;

    User user = new User();
    user.setName(dto.name());
    user.setEmail(dto.email());
    user.setPassword(dto.password() != "" ? passwordEncoder.encode(dto.password()) : null);
    user.setRole(dto.role() != null ? dto.role() : Role.USER);

    return user;
  }

  public UserDTO toDTO(User user) {
    if (user == null) return null;

    return new UserDTO(
      user.getName(),
      user.getEmail(),
      user.getPassword(),
      user.getRole()
    );
  }

  public UserResponseDTO toResponseDTO(User user) {
    return new UserResponseDTO(
      user.getName(),
      user.getEmail(),
      user.getCreatedAt(),
      user.getUpdatedAt()
    );
  }

  public User mergeToEntity(User user, UserDTO dto) {
    user.setName(dto.name() != null ? dto.name() : user.getName());
    user.setEmail(dto.email() != null ? dto.email() : user.getEmail());
    user.setPassword(dto.password() != null && dto.password() != "" ? passwordEncoder.encode(dto.password()) : user.getPassword());
    user.setRole(dto.role() != null ? dto.role() : user.getRole());

    return user;
  }
}
