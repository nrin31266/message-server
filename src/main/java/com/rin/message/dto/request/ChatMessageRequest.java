package com.rin.message.dto.request;

import com.rin.message.constant.ChatType;
import com.rin.message.constant.MessageType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessageRequest {
    String senderId;
    String receiverId;
    String content;
    MessageType messageType;
    Long conversationId;

    ChatType chatType;

    List<AttachmentRequest> attachments;
}
