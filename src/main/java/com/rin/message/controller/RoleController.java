package com.rin.message.controller;

import com.rin.message.dto.ApiResponse;
import com.rin.message.dto.request.CreateRoleRequest;
import com.rin.message.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
public class RoleController {
    RoleService roleService;
    public ApiResponse create(@RequestBody CreateRoleRequest createRoleRequest) {
        roleService.save(createRoleRequest);
        return ApiResponse.builder().build();
    }
}
