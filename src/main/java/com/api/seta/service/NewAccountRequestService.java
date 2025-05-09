package com.api.seta.service;

import com.api.seta.dto.NewAccountRequestDTO;
import com.api.seta.model.MailExtension;
import com.api.seta.model.NewAccountRequest;
import com.api.seta.repository.MailExtensionRepository;
import com.api.seta.repository.NewAccountRequestRepository;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NewAccountRequestService {

  private final MailExtensionRepository mailExtensionRepository;
  private final NewAccountRequestRepository repository;

  public NewAccountRequestService(MailExtensionRepository mailExtensionRepository, NewAccountRequestRepository repository) {
    this.mailExtensionRepository = mailExtensionRepository;
    this.repository = repository;
  }


  public Page<NewAccountRequest> findAll(String search, Pageable pageable) {
    if (search != null && !search.isBlank()) {
      return repository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search, pageable);
    }
    return repository.findAll(pageable);
  }

  public NewAccountRequest findByEmail(String email) {
    return repository.findByEmail(email).orElse(null);
  }

  public NewAccountRequest findByEmailAndToken(String email, String token) {
    return repository.findByEmailAndToken(email, token).orElse(null);
  }

  public NewAccountRequest create(NewAccountRequestDTO dto) {
    String email = dto.email();
    String domain = extractDomain(email);

    Optional<MailExtension> validExtension = mailExtensionRepository.findByMailExtension(domain);

    if (validExtension.isEmpty()) throw new IllegalArgumentException("E-mail não autorizado");
    if (repository.existsByEmail(email)) throw new IllegalArgumentException("Já existe uma solicitação pendente para esse e-mail.");

    String token = UUID.randomUUID().toString();

    NewAccountRequest request = new NewAccountRequest();
    request.setEmail(email);
    request.setName(dto.name());
    request.setToken(token);

    return request;
  }

  public NewAccountRequest store(NewAccountRequest request) {
    return repository.save(request);
  }

  private String extractDomain(String email) {
    int atIndex = email.lastIndexOf("@");
    if (atIndex == -1) throw new IllegalArgumentException("E-mail inválido");

    return email.substring(atIndex);
  }

  public void delete(NewAccountRequest request) {
    repository.delete(request);
  }
}
