package com.api.seta.repository;

import com.api.seta.model.Role;
import com.api.seta.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);
  User findByEmail(String email);
  Page<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email, Pageable pageable);
  @Query("""
    SELECT u FROM User u
    WHERE (LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')))
      AND u.role = :role
    """)
  Page<User> searchByNameOrEmailAndRole(@Param("search") String search, @Param("role") Role role, Pageable pageable);
  Page<User> findByRole(Role role, Pageable pageable);
}
