package com.rin.message.service;

import com.rin.message.constant.MessageType;
import com.rin.message.constant.Status;
import com.rin.message.dto.request.ChatMessageRequest;
import com.rin.message.dto.response.MessageResponse;
import com.rin.message.entity.ChatMember;
import com.rin.message.entity.Conversation;
import com.rin.message.entity.Message;
import com.rin.message.entity.MessageStatus;
import com.rin.message.exception.AppException;
import com.rin.message.exception.ErrorCode;
import com.rin.message.mapper.MessageMapper;
import com.rin.message.repository.ConversationRepository;
import com.rin.message.repository.MessageRepository;
import com.rin.message.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ChatService {
    ConversationRepository conversationRepository;
    MessageRepository messageRepository;
    UserRepository userRepository;
    MessageMapper messageMapper;

    public MessageResponse saveMessage(ChatMessageRequest request, boolean isGroup) {
        Conversation conversation;

        if (!isGroup) {
            Optional<Conversation> conversationOptional = conversationRepository.findConversation(request.getSenderId(), request.getReceiverId());
            if (conversationOptional.isEmpty()) {
                Conversation conversationEntity = Conversation.builder()
                        .createdBy(request.getSenderId())
                        .chatMembers(new ArrayList<>()) // Khởi tạo danh sách rỗng
                        .build();

                // Tạo các ChatMember và gán liên kết
                ChatMember sender = ChatMember.builder()
                        .user(userRepository.findById(request.getSenderId())
                                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)))
                        .conversation(conversationEntity)
                        .build();

                ChatMember receiver = ChatMember.builder()
                        .user(userRepository.findById(request.getReceiverId())
                                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)))
                        .conversation(conversationEntity)
                        .build();

                // Thêm các ChatMember vào Conversation
                conversationEntity.setChatMembers(List.of(sender, receiver));

                // Lưu Conversation, sẽ tự động lưu ChatMember nhờ CascadeType.ALL
                conversation = conversationRepository.save(conversationEntity);

            } else {
                conversation = conversationOptional.get();
            }
        } else {
            conversation = conversationRepository.findById(Long.parseLong(request.getReceiverId()))
                    .orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));
        }

        Message message = messageMapper.toMessage(request);
        message.setConversation(conversation);


        MessageStatus messageStatus = MessageStatus.builder()
                .status(Status.SENT)
                .message(message)
                .build();
        message.setMessageStatus(messageStatus);

        if (message.getMessageType() != MessageType.TEXT) {
            log.warn("Handling for non-text messages is not implemented yet.");
            throw new UnsupportedOperationException("Message type not supported yet.");
        }

        message = messageRepository.save(message);
        return messageMapper.toMessageResponse(message);
    }


    public List<MessageResponse> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream().map(messageMapper::toMessageResponse).toList();
    }
}
