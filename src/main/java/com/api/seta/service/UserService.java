package com.api.seta.service;

import com.api.seta.model.User;
import com.api.seta.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public Page<User> findAll(String search, Pageable pageable) {
    if (search == null || search.isBlank()) return repository.findAll(pageable);

    return repository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search, pageable);
  }

  public User findById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public User findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public User store(User user) {
    return repository.save(user);
  }

  public void delete(User user) {
    repository.delete(user);
  }
}
