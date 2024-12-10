package com.rin.message.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    @NotBlank(message = "Username not blank")
    @NotNull(message = "Username not null")
    @NotEmpty(message = "Username not empty")
    @Min(message = "Username can not less 4 characters", value = 4)
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
