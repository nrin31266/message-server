package com.rin.message.entity;

import com.rin.message.constant.MessageType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String senderId;
    String receiverId;
    @Enumerated(EnumType.STRING)
    MessageType messageType;
    String content;
    Instant createdAt;
    Instant updatedAt;

    // Mối quan hệ One-to-One với MessageReadStatus
    @OneToOne(mappedBy = "message", cascade = CascadeType.ALL)
    MessageStatus messageStatus;

    // Mối quan hệ One-to-Many với Attachment
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Attachment> attachments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    Conversation conversation;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
