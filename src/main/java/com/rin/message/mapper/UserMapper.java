package com.rin.message.mapper;

import com.rin.message.dto.request.UpdateProfileRequest;
import com.rin.message.dto.request.CreateUserRequest;
import com.rin.message.dto.response.UserResponse;
import com.rin.message.entity.Profile;
import com.rin.message.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserRequest request);
    UserResponse toUserResponse(User user);
    Profile toProfile(CreateUserRequest request);
}
