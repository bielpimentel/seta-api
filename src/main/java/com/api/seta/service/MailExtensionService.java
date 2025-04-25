package com.api.seta.service;

import com.api.seta.model.MailExtension;
import com.api.seta.repository.MailExtensionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MailExtensionService {

  private final MailExtensionRepository repository;

  public MailExtensionService(MailExtensionRepository repository) {
    this.repository = repository;
  }

  public List<MailExtension> findAll() {
    return repository.findAll();
  }

  public MailExtension save(MailExtension extension) {
    return repository.save(extension);
  }
}
