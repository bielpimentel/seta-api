package com.api.seta.controller;

import com.api.seta.annotation.CurrentUser;
import com.api.seta.dto.QRCodeDTO;
import com.api.seta.model.User;
import com.api.seta.service.AccessLogService;
import com.api.seta.service.QRTokenService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

  private final QRTokenService qrTokenService;
  private final AccessLogService accessLogService;

  public QRCodeController(
    QRTokenService qrTokenService, 
    AccessLogService accessLogService 
  ) {
    this.qrTokenService = qrTokenService;
    this.accessLogService = accessLogService;
  }

  @GetMapping("/generate")
  public ResponseEntity<QRCodeDTO> generateQRCode(@CurrentUser User user) {
    if (user == null) return ResponseEntity.status(401).build();
    if (accessLogService.hasRecentAccess(user)) return ResponseEntity.badRequest().build();

    String token = qrTokenService.generateToken(user);
    return ResponseEntity.ok(new QRCodeDTO(token));
  }
}
