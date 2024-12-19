package com.rin.message.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentRequest {
    // Thông tin thêm nếu là file
    String filePath;
    String fileName;
    String fileType;
    double fileSize;
}
