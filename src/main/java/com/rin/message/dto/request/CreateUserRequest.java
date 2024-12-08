package com.rin.message.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    String username;
    String password;
    String email;

    String firstName;
    String lastName;
    @JsonFormat(pattern = "MM-dd-yyyy")
    LocalDate dob;
    String gender;
    String phone;
    String avatar;
}
