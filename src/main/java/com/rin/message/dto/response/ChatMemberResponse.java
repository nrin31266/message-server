package com.rin.message.dto.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rin.message.constant.ChatRole;
import com.rin.message.entity.Conversation;
import com.rin.message.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMemberResponse {
    Long id;
    ChatRole role;
    UserResponse user;
    Instant joinedAt;
    Instant updatedAt;
    Instant leftAt;
}
