package com.api.seta.controller;

import com.api.seta.annotation.CurrentUser;
import com.api.seta.dto.AccessLogDTO;
import com.api.seta.dto.QRCodeDTO;
import com.api.seta.mapper.UserMapper;
import com.api.seta.model.AccessLog;
import com.api.seta.model.AccessType;
import com.api.seta.model.User;
import com.api.seta.service.AccessLogService;
import com.api.seta.service.QRTokenService;
import com.api.seta.service.UsedTokenService;
import com.api.seta.service.UserService;

import jakarta.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

  private final QRTokenService qrTokenService;
  private final AccessLogService accessLogService;
  private final UserService userService;
  private final UserMapper userMapper;
  private final UsedTokenService usedTokenService;

  public QRCodeController(
    QRTokenService qrTokenService, 
    AccessLogService accessLogService, 
    UserService userService,
    UserMapper userMapper,
    UsedTokenService usedTokenService
  ) {
    this.qrTokenService = qrTokenService;
    this.accessLogService = accessLogService;
    this.userService = userService;
    this.userMapper = userMapper;
    this.usedTokenService = usedTokenService;
  }

  @GetMapping("/generate")
  public ResponseEntity<QRCodeDTO> generateQRCode(@CurrentUser User user) {
    if (user == null) return ResponseEntity.status(401).build();
    if (accessLogService.hasRecentAccess(user)) return ResponseEntity.badRequest().build();

    String token = qrTokenService.generateToken(user);
    return ResponseEntity.ok(new QRCodeDTO(token));
  }

  @PostMapping("/read")
  @Transactional
  public ResponseEntity<AccessLogDTO> readQRCode(@RequestBody QRCodeDTO dto) {
    if (usedTokenService.isTokenUsed(dto.token())) 
      return ResponseEntity.status(409).build();

    Long userId = qrTokenService.getUserIdFromToken(dto.token());
    AccessType accessType = qrTokenService.getAccessTypeFromToken(dto.token());
    User user = userService.findById(userId);

    if (qrTokenService.isTokenExpired(dto.token()) || user == null || accessLogService.hasRecentAccess(user) || user.getQrCodeType() != accessType)
      return ResponseEntity.badRequest().build();
      
    AccessLog log = new AccessLog();
    log.setUser(user);
    log.setType(accessType);
    accessLogService.store(log);

    user.setQrCodeType(accessType == AccessType.ENTRADA 
      ? AccessType.SAIDA 
      : AccessType.ENTRADA);

    userService.store(user);
    usedTokenService.store(dto.token());

    AccessLogDTO response = new AccessLogDTO(
      userMapper.toResponseDTO(user),
      accessType,
      log.getAccessDateTime(),
      accessType == AccessType.ENTRADA
        ? "Entrada registrada com sucesso!"
        : "Saída registrada com sucesso!"
    );

    return ResponseEntity.ok(response);
  }
}
