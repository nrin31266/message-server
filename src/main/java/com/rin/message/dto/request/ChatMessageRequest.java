package com.rin.message.dto.request;

import com.rin.message.constant.MessageType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessageRequest {
    String senderId;         // ID của người gửi
    String receiverId;       // ID của người nhận
    String content;          // Nội dung tin nhắn
    Instant timestamp;       // Thời gian gửi tin nhắn
    MessageType messageType; // Loại tin nhắn (text, image, file, ...)
    Long conversationId;     // ID của cuộc trò chuyện (nếu đã có)
    // Thông tin thêm nếu là file
    String filePath;
    String fileName;
    String fileType;
    double fileSize;
}
