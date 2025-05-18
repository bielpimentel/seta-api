package com.api.seta.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.seta.dto.AccessLogAdminDTO;
import com.api.seta.mapper.AccessLogMapper;
import com.api.seta.model.AccessLog;
import com.api.seta.model.AccessType;
import com.api.seta.service.AccessLogService;

@RestController
@RequestMapping("admin/access-logs")
public class AdminAccessLogController {

  private final AccessLogService accessLogService;
  private final AccessLogMapper mapper;

  public AdminAccessLogController(AccessLogService accessLogService, AccessLogMapper mapper) {
    this.accessLogService = accessLogService;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<Page<AccessLogAdminDTO>> getAll(
      @RequestParam(required = false) String search,
      @RequestParam(required = false) AccessType type,
      @PageableDefault(size = 10, sort = "id") Pageable pageable
  ) {
    Page<AccessLog> accessLogs = accessLogService.findAll(search, type, pageable);
    return ResponseEntity.ok(mapper.toAdminResponseDTO(accessLogs));
  }
}
