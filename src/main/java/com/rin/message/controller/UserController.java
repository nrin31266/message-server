package com.rin.message.controller;

import com.rin.message.dto.ApiResponse;
import com.rin.message.dto.PageResponse;
import com.rin.message.dto.request.CreateUserRequest;
import com.rin.message.dto.response.UserResponse;
import com.rin.message.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Validated CreateUserRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping("/{username}")
    public ApiResponse<UserResponse> getUserByUsername(@PathVariable("username") String username){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserUsername(username))
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUserByUsername(){
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<PageResponse<UserResponse>> search(@RequestParam int page, @RequestParam(required = false) String keyword){
        return ApiResponse.<PageResponse<UserResponse>>builder()
                .result(userService.search(page, 10, keyword))
                .build();
    }
}
