package com.api.seta.repository;

import com.api.seta.model.AccessLog;
import com.api.seta.model.AccessType;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
  boolean existsByUserIdAndTypeAndAccessDateTimeBetween(
    Long userId,
    AccessType type,
    LocalDateTime start,
    LocalDateTime end
  );

  Page<AccessLog> findByUserNameContainingIgnoreCaseOrUserEmailContainingIgnoreCase(
    String name,
    String email,
    Pageable pageable
  );

  Page<AccessLog> findByType(AccessType type, Pageable pageable);

  @Query("""
      SELECT a FROM AccessLog a 
      WHERE (:search IS NULL 
                OR LOWER(a.user.name) LIKE LOWER(CONCAT('%', :search, '%')) 
                OR LOWER(a.user.email) LIKE LOWER(CONCAT('%', :search, '%')))
        AND (:type IS NULL OR a.type = :type)
    """)
  Page<AccessLog> searchByUserNameOrEmailAndType(
    @Param("search") String search,
    @Param("type") AccessType type,
    Pageable pageable
  );
}
