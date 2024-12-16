package com.rin.message.dto.response;

import com.rin.message.entity.Profile;
import com.rin.message.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String email;
    Profile profile;
    boolean enabled;
    Instant createdAt;
    Instant updatedAt;
    List<Role> roles;
}
