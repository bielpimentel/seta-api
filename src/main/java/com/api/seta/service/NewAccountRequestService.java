package com.api.seta.service;

import com.api.seta.model.NewAccountRequest;
import com.api.seta.repository.NewAccountRequestRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NewAccountRequestService {

  private final NewAccountRequestRepository repository;

  public NewAccountRequestService(NewAccountRequestRepository repository) {
    this.repository = repository;
  }

  public List<NewAccountRequest> findAll() {
    return repository.findAll();
  }

  public NewAccountRequest save(NewAccountRequest request) {
    return repository.save(request);
  }
}
