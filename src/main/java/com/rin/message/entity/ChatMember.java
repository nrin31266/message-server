package com.rin.message.entity;

import com.rin.message.constant.ChatRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String userId;

    @Enumerated(EnumType.STRING)
    ChatRole role;

    // Thêm trường liên kết với Conversation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    Conversation conversation;

    Instant joinedAt;
    Instant updatedAt;
    Instant leftAt;

    @PrePersist
    protected void onCreate() {
        joinedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}

