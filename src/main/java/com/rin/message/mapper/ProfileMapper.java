package com.rin.message.mapper;

import com.rin.message.dto.response.ProfileResponse;
import com.rin.message.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(target = "userId", ignore = true)
    ProfileResponse toProfileResponse(Profile profile);
}
