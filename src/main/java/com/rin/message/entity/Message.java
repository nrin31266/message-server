package com.rin.message.entity;

import com.rin.message.constant.MessageType;
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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // Mối quan hệ nhiều - một với Conversation, khóa ngoại là "conversation_id"
    @ManyToOne
    @JoinColumn(name = "conversation_id") // Đổi tên cột khóa ngoại cho chính xác
    Conversation conversation;

    // Mối quan hệ nhiều - một với User, khóa ngoại là "sender_id"
    @ManyToOne
    @JoinColumn(name = "sender_id") // Đổi tên cột khóa ngoại cho chính xác
    User sender;

    // Enum cho loại tin nhắn
    @Enumerated(EnumType.STRING)
    MessageType messageType;

    // Nội dung tin nhắn
    String content;

    // Thời gian tạo và cập nhật tin nhắn
    Instant createdAt;
    Instant updatedAt;

    // Phương thức tự động thiết lập thời gian tạo khi bản ghi được persist
    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

    // Phương thức tự động thiết lập thời gian cập nhật khi bản ghi được update
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
