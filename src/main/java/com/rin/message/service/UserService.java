package com.rin.message.service;


import com.rin.message.dto.request.CreateUserRequest;
import com.rin.message.dto.response.UserResponse;
import com.rin.message.entity.Profile;
import com.rin.message.entity.User;
import com.rin.message.mapper.UserMapper;
import com.rin.message.repository.ProfileRepository;
import com.rin.message.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserMapper userMapper;
    UserRepository userRepository;
    ProfileRepository profileRepository;
    PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public UserResponse createUser(CreateUserRequest request) {
        Optional<User> existingUser = userRepository.findByUsernameOrEmail(request.getUsername(), request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username or email already exists");
        }

        User user = userMapper.toUser(request);

        if(request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        try {
            user = userRepository.save(user);
        }catch (Exception e) {
            throw new RuntimeException("Failed to save user");
        }
        Profile profile = userMapper.toProfile(request);
        profile.setUserId(user.getId());
        profileRepository.save(profile);
        return userMapper.toUserResponse(user);
    }
}
