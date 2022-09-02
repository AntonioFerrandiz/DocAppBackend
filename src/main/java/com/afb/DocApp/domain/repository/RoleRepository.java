package com.afb.DocApp.domain.repository;

import com.afb.DocApp.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
