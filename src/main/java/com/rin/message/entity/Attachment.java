package com.rin.message.entity;

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
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String filePath;
    String fileName;
    String fileType;
    double fileSize;
    Instant uploadAt;

    // Thêm trường liên kết với Message
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    Message message;

    @PrePersist
    protected void onCreate() {
        uploadAt = Instant.now();
    }
}
