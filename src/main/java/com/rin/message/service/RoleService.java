package com.rin.message.service;

import com.rin.message.dto.request.CreateRoleRequest;
import com.rin.message.entity.Permission;
import com.rin.message.entity.Role;
import com.rin.message.repository.PermissionRepository;
import com.rin.message.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;

    public void save(CreateRoleRequest request) {
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());

        Role role = Role.builder()
                .name(request.getName())
                .description(request.getDescription())
                .permissions(permissions)
                .build();


        roleRepository.save(role);

    }
}
