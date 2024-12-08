package com.rin.message.service;

import com.rin.message.dto.request.CreatePermissionRequest;
import com.rin.message.entity.Permission;
import com.rin.message.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    public void createPermission(CreatePermissionRequest request) {
        permissionRepository.save(new Permission(request.getName(), request.getDescription()));
    }
}
