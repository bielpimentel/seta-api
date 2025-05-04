package com.api.seta.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.seta.dto.SupportAdminResponseDTO;
import com.api.seta.mapper.SupportMapper;
import com.api.seta.model.Support;
import com.api.seta.service.SupportService;

@RestController
@RequestMapping("admin/supports")
public class AdminSupportController {

  private final SupportService supportService;
  private final SupportMapper mapper;

  public AdminSupportController(SupportService supportService, SupportMapper mapper) {
    this.mapper = mapper;
    this.supportService = supportService;
  }

  @GetMapping
  public ResponseEntity<Page<SupportAdminResponseDTO>> getAll(
      @RequestParam(required = false) String search,
      @PageableDefault(size = 10, sort = "id") Pageable pageable
  ) {
    Page<Support> supports = supportService.findAll(search, pageable);

    return ResponseEntity.ok(mapper.toAdminResponseDTO(supports));
  }

  @GetMapping("/{id}")
  public ResponseEntity<SupportAdminResponseDTO> getById(@PathVariable Long id) {
    Support support = supportService.findById(id);

    if (support == null) return ResponseEntity.notFound().build();

    return ResponseEntity.ok(mapper.toAdminResponseDTO(support));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    Support support = supportService.findById(id);

    if (support == null) return ResponseEntity.notFound().build();

    supportService.delete(support);
    return ResponseEntity.noContent().build();
  }
}
