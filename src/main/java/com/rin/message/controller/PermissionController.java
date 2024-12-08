package com.rin.message.controller;

import com.rin.message.dto.ApiResponse;
import com.rin.message.dto.request.CreatePermissionRequest;
import com.rin.message.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    public ApiResponse create(@RequestBody CreatePermissionRequest request) {
        permissionService.createPermission(request);
        return ApiResponse.builder().build();
    }
}
