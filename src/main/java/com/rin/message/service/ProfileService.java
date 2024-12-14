package com.rin.message.service;

import com.rin.message.dto.response.UserResponse;
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

    public UserResponse getMyInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }
}
