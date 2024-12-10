package com.rin.message.service;

import com.rin.message.dto.response.MyInfoResponse;
import com.rin.message.entity.Profile;
import com.rin.message.entity.User;
import com.rin.message.exception.AppException;
import com.rin.message.exception.ErrorCode;
import com.rin.message.mapper.ProfileMapper;
import com.rin.message.mapper.UserMapper;
import com.rin.message.repository.ProfileRepository;
import com.rin.message.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileService {
    ProfileRepository profileRepository;
    UserRepository userRepository;
    UserMapper userMapper;
    ProfileMapper profileMapper;

    public MyInfoResponse getMyInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        log.warn(userId);
        User user = userRepository.findById(userId).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        Profile profile = profileRepository.findByUserId(userId).orElse(null);
        return MyInfoResponse.builder()
                .profile(profileMapper.toProfileResponse(profile))
                .user(userMapper.toUserResponse(user))
                .build();
    }
}
