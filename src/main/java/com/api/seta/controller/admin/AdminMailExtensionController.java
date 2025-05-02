package com.api.seta.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.seta.dto.MailExtensionDTO;
import com.api.seta.mapper.MailExtensionMapper;
import com.api.seta.model.MailExtension;
import com.api.seta.service.MailExtensionService;

@RestController
@RequestMapping("admin/mail-extensions")
public class AdminMailExtensionController {

  private final MailExtensionService mailExtensionService;
  private final MailExtensionMapper mailExtensionMapper;

  public AdminMailExtensionController(MailExtensionService mailExtensionService, MailExtensionMapper mailExtensionMapper) {
    this.mailExtensionMapper = mailExtensionMapper;
    this.mailExtensionService = mailExtensionService;
  }

  @GetMapping
  public ResponseEntity<Page<MailExtension>> getAll(
      @RequestParam(required = false) String search,
      @PageableDefault(size = 10, sort = "id") Pageable pageable
  ) {
    Page<MailExtension> mailExtensions = mailExtensionService.findAll(search, pageable);

    return ResponseEntity.ok(mailExtensions);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MailExtension> getById(@PathVariable Long id) {
    MailExtension mailExtension = mailExtensionService.findById(id);

    if (mailExtension == null) return ResponseEntity.notFound().build();

    return ResponseEntity.ok(mailExtension);
  }

  @PostMapping
  public ResponseEntity<MailExtension> create(@RequestBody MailExtensionDTO dto) {
    MailExtension mailExtension = mailExtensionMapper.toEntity(dto);
    mailExtensionService.store(mailExtension);

    return ResponseEntity.created(null).body(mailExtension);
  }

  @PutMapping("/{id}")
  public ResponseEntity<MailExtension> update(@PathVariable Long id, @RequestBody MailExtensionDTO dto) {
    MailExtension existingMailExtension = mailExtensionService.findById(id);

    if (existingMailExtension == null) return ResponseEntity.notFound().build();

    MailExtension mergedMailExtension = mailExtensionMapper.mergeToEntity(existingMailExtension, dto);
    MailExtension updatedMailExtension = mailExtensionService.store(mergedMailExtension);

    return ResponseEntity.ok(updatedMailExtension);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    MailExtension mailExtension = mailExtensionService.findById(id);

    if (mailExtension == null) return ResponseEntity.notFound().build();

    mailExtensionService.delete(mailExtension);
    return ResponseEntity.noContent().build();
  }
}
