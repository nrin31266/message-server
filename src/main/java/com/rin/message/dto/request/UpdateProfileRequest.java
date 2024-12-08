package com.rin.message.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateProfileRequest {
    String firstName;
    String lastName;
    LocalDate dob;
    String gender;
    String phone;
    String avatar;
}
