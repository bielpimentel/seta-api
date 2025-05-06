package com.api.seta.controller;

import com.api.seta.dto.NewAccountRequestDTO;
import com.api.seta.model.NewAccountRequest;
import com.api.seta.service.EmailService;
import com.api.seta.service.NewAccountRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/new-account")
public class NewAccountRequestController {

  private final NewAccountRequestService service;
  private final EmailService emailService;

  public NewAccountRequestController(NewAccountRequestService service, EmailService emailService) {
    this.emailService = emailService;
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<NewAccountRequest> requestNewAccount(@RequestBody NewAccountRequestDTO dto) {
    NewAccountRequest newAccount = service.create(dto);
    if (newAccount == null) return ResponseEntity.badRequest().body(null);

    service.store(newAccount);
    emailService.newAccountMail(newAccount);

    return ResponseEntity.created(null).body(newAccount);
  }
}