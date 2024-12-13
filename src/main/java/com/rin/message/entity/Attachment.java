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
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "message_id")
    Message messageId;
    String filePath;
    String fileName;
    String fileType;
    Instant uploadTime;

    @PrePersist
    protected void onCreate() {
        uploadTime = Instant.now();
    }
}
