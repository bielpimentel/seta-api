package com.api.seta.service;

import com.api.seta.model.User;
import com.api.seta.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public User store(User user) {
    return repository.save(user);
  }

  public void delete(User user) {
    repository.delete(user);
  }
}
