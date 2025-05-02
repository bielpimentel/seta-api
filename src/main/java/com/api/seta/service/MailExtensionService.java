package com.api.seta.service;

import com.api.seta.model.MailExtension;
import com.api.seta.repository.MailExtensionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MailExtensionService {

  private final MailExtensionRepository repository;

  public MailExtensionService(MailExtensionRepository repository) {
    this.repository = repository;
  }

  public Page<MailExtension> findAll(String search, Pageable pageable) {
    if (search == null || search.isBlank()) return repository.findAll(pageable);

    return repository.findByMailExtensionContainingIgnoreCase(search, pageable);
  }

  public MailExtension findById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public MailExtension store(MailExtension extension) {
    return repository.save(extension);
  }

  public void delete(MailExtension extension) {
    repository.delete(extension);
  }
}
