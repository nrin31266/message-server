package com.rin.message.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rin.message.entity.ChatMember;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationResponse {
    Long id;
    String name;
    boolean isGroup;
    String createdBy;
    Instant createdAt;
    Instant updatedAt;
    List<ChatMemberResponse> chatMembers;
}
