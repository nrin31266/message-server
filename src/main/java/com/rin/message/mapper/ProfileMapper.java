package com.rin.message.mapper;

import com.rin.message.dto.response.ProfileResponse;
import com.rin.message.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileResponse toProfileResponse(Profile profile);
}
