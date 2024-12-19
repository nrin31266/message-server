
package com.rin.message.dto.response;

import com.rin.message.constant.MessageType;
import com.rin.message.entity.Attachment;
import com.rin.message.entity.Conversation;
import com.rin.message.entity.MessageStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageResponse {
    Long id;
    String senderId;
    String receiverId;
    MessageType messageType;
    String content;
    Instant createdAt;
    Instant updatedAt;
    MessageStatus messageStatus;
    List<AttachmentResponse> attachments;
}
