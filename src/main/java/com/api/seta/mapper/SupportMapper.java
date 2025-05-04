package com.api.seta.mapper;

import com.api.seta.dto.SupportAdminResponseDTO;
import com.api.seta.dto.SupportDTO;
import com.api.seta.dto.SupportResponseDTO;
import com.api.seta.model.Support;
import com.api.seta.model.User;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class SupportMapper {

  public Support toEntity(SupportDTO dto, User user) {
    Support support = new Support();
    support.setUser(user);
    support.setPhoneNumber(dto.phoneNumber());
    support.setSubject(dto.subject());
    support.setMessage(dto.message());
    return support;
  }

  public SupportResponseDTO toResponseDTO(Support support) {
    return new SupportResponseDTO(
      support.getUser().getName(),
      support.getUser().getEmail(),
      support.getPhoneNumber(), 
      support.getSubject(), 
      support.getMessage(),
      support.getCreatedAt()
    );
  }

  public Page<SupportResponseDTO> toResponseDTO(Page<Support> supports) {
    return supports.map(support -> toResponseDTO(support));
  }

  public SupportAdminResponseDTO toAdminResponseDTO(Support support) {
    return new SupportAdminResponseDTO(
      support.getId(),
      support.getUser().getId(),
      support.getUser().getName(),
      support.getUser().getEmail(),
      support.getPhoneNumber(), 
      support.getSubject(), 
      support.getMessage(),
      support.getCreatedAt()
    );
  }

  public Page<SupportAdminResponseDTO> toAdminResponseDTO(Page<Support> supports) {
    return supports.map(support -> toAdminResponseDTO(support));
  }
}
