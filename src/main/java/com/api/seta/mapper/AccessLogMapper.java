package com.api.seta.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.api.seta.dto.AccessLogAdminDTO;
import com.api.seta.model.AccessLog;

@Component
public class AccessLogMapper {

  public AccessLogAdminDTO toAdminResponseDTO(AccessLog accessLog) {
    return new AccessLogAdminDTO(
      accessLog.getId(),
      accessLog.getUser().getName(),
      accessLog.getUser().getEmail(),
      accessLog.getType(),
      accessLog.getAccessDateTime()
    );
  }

  public Page<AccessLogAdminDTO> toAdminResponseDTO(Page<AccessLog> accessLogs) {
    return accessLogs.map(accessLog -> toAdminResponseDTO(accessLog));
  }
}
