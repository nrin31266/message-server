package com.rin.message.controller;



import com.rin.message.dto.ApiResponse;
import com.rin.message.dto.response.MyInfoResponse;
import com.rin.message.service.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profiles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
public class ProfileController {
    ProfileService profileService;

    @PostMapping("/my-info")
    public ApiResponse<MyInfoResponse> getMyInfo() {
        return ApiResponse.<MyInfoResponse>builder()
                .result(profileService.getMyInfo())
                .build();
    }

}
