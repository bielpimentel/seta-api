package com.api.seta.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.api.seta.dto.NewAccountRequestAdminResponseDTO;
import com.api.seta.model.NewAccountRequest;

@Component
public class NewAccountRequestMapper {
  public NewAccountRequestAdminResponseDTO toAdminResponseDTO(NewAccountRequest request) {
    return new NewAccountRequestAdminResponseDTO(
      request.getEmail(),
      request.getName(),
      request.getCreatedAt()
    );
  }

  public Page<NewAccountRequestAdminResponseDTO> toAdminResponseDTO(Page<NewAccountRequest> requests) {
    return requests.map(request -> toAdminResponseDTO(request));
  }
}
