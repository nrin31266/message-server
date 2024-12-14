package com.rin.message.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    @NotBlank(message = "Username not blank")
    @NotNull(message = "Username not null")
    @NotEmpty(message = "Username not empty")
    @Size(min = 4, message = "Username must be at least 4 characters long")
    String username;
    String password;
    String email;

    String firstName;
    String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dob;
    String gender;
    String phone;
    String avatar;

}
