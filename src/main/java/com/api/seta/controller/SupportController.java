package com.api.seta.controller;

import com.api.seta.annotation.CurrentUser;
import com.api.seta.dto.SupportDTO;
import com.api.seta.dto.SupportResponseDTO;
import com.api.seta.mapper.SupportMapper;
import com.api.seta.model.Support;
import com.api.seta.model.User;
import com.api.seta.service.SupportService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supports")
public class SupportController {

  private final SupportService service;
  private final SupportMapper mapper;

  public SupportController(SupportService service, SupportMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<Page<SupportResponseDTO>> getMySupports(@CurrentUser User user, Pageable pageable) {
    if (user == null) return ResponseEntity.status(401).build();

    Page<Support> supports = service.findByUser(user, pageable);

    return ResponseEntity.ok(mapper.toResponseDTO(supports));
  }

  @PostMapping
  public ResponseEntity<SupportResponseDTO> create(@RequestBody SupportDTO dto, @CurrentUser User user) {
    if (user == null) return ResponseEntity.status(401).build();

    Support support = mapper.toEntity(dto, user);
    Support saved = service.store(support);
    
    return ResponseEntity.ok(mapper.toResponseDTO(saved));
  }
}
