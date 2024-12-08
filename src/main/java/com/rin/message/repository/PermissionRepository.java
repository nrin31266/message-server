package com.rin.message.repository;

import com.rin.message.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, String> {
    List<Permission> findAllByName(String names);
}
