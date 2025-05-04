package com.api.seta.service;

import com.api.seta.model.Support;
import com.api.seta.model.User;
import com.api.seta.repository.SupportRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupportService {

  private final SupportRepository repository;

  public SupportService(SupportRepository repository) {
    this.repository = repository;
  }

  public Page<Support> findAll(String search, Pageable pageable) {
    if (search != null && !search.isBlank()) {
      return repository.findByUserNameContainingIgnoreCaseOrUserEmailContainingIgnoreCase(search, search, pageable);
    }
    return repository.findAll(pageable);
  }

  public Support findById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public Page<Support> findByUser(User user, Pageable pageable) {
    return repository.findByUser(user, pageable);
  }

  public Support store(Support support) {
    return repository.save(support);
  }

  public void delete(Support support) {
    repository.delete(support);
  }
}