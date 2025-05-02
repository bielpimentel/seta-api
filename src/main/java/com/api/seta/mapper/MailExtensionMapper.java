package com.api.seta.mapper;

import org.springframework.stereotype.Component;

import com.api.seta.dto.MailExtensionDTO;
import com.api.seta.model.MailExtension;

@Component
public class MailExtensionMapper {

  public MailExtensionDTO toDTO(MailExtension mailExtension) {
    if (mailExtension == null) return null;

    return new MailExtensionDTO(mailExtension.getMailExtension());
  }

  public MailExtension toEntity(MailExtensionDTO dto) {
    if (dto == null) return null;

    MailExtension mailExtension = new MailExtension();
    mailExtension.setMailExtension(dto.mailExtension());

    return mailExtension;
  }

  public MailExtension mergeToEntity(MailExtension mailExtension, MailExtensionDTO dto) {
    mailExtension.setMailExtension(dto.mailExtension() != null ? dto.mailExtension() : mailExtension.getMailExtension());

    return mailExtension;
  }
}
