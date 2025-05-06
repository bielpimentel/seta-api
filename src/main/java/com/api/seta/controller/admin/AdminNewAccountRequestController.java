package com.api.seta.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.seta.dto.NewAccountRequestAdminResponseDTO;
import com.api.seta.dto.NewAccountRequestDTO;
import com.api.seta.mapper.NewAccountRequestMapper;
import com.api.seta.model.NewAccountRequest;
import com.api.seta.service.EmailService;
import com.api.seta.service.NewAccountRequestService;

@RestController
@RequestMapping("admin/pendings")
public class AdminNewAccountRequestController {

  private final NewAccountRequestService service;
  private final NewAccountRequestMapper mapper;
  private final EmailService emailService;

  public AdminNewAccountRequestController(
      NewAccountRequestService service,
      NewAccountRequestMapper mapper,
      EmailService emailService
  ) {
    this.service = service;
    this.mapper = mapper;
    this.emailService = emailService;
  }

  @GetMapping
  public ResponseEntity<Page<NewAccountRequestAdminResponseDTO>> getAll(
      @RequestParam(required = false) String search,
      @PageableDefault(size = 10, sort = "email") Pageable pageable
  ) {
    Page<NewAccountRequest> requests = service.findAll(search, pageable);

    return ResponseEntity.ok(mapper.toAdminResponseDTO(requests));
  }

  @PostMapping
  public ResponseEntity<NewAccountRequestAdminResponseDTO> resendMail(@RequestBody NewAccountRequestDTO dto) {
    NewAccountRequest newAccount = service.findByEmail(dto.email());
    if (newAccount == null) return ResponseEntity.notFound().build();

    emailService.resendNewAccountMail(newAccount);

    return ResponseEntity.ok(mapper.toAdminResponseDTO(newAccount));
  }

  @DeleteMapping("/{email}")
  public ResponseEntity<Void> delete(@PathVariable String email) {
    NewAccountRequest request = service.findByEmail(email);

    if (request == null) return ResponseEntity.notFound().build();

    service.delete(request);
    return ResponseEntity.noContent().build();
  }
}
