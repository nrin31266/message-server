package com.rin.message.dto.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rin.message.entity.Message;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentResponse {
    Long id;
    String filePath;
    String fileName;
    String fileType;
    double fileSize;
    Instant uploadAt;
}
