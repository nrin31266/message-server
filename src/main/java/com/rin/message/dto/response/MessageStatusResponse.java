package com.rin.message.dto.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rin.message.constant.Status;
import com.rin.message.entity.Message;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageStatusResponse {
    Long id;
    Status status;
    Instant updatedAt;
}
