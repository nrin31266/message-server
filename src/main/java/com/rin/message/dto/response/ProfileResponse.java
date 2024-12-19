package com.rin.message.dto.response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileResponse {
    Long id;
    String userId;
    String firstName;
    String lastName;
    LocalDate dob;
    String gender;
    String phone;
    String avatar;
    Instant createdAt;
    Instant updatedAt;
}
