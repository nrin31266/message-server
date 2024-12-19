package com.rin.message.service;

import com.rin.message.constant.ChatType;
import com.rin.message.constant.MessageType;
import com.rin.message.constant.Status;
import com.rin.message.dto.PageResponse;
import com.rin.message.dto.request.ChatMessageRequest;
import com.rin.message.dto.response.MessageResponse;
import com.rin.message.dto.response.UserResponse;
import com.rin.message.entity.*;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public MessageResponse saveMessage(ChatMessageRequest request) {
        Conversation conversation;
        if (request.getChatType() == ChatType.PERSONAL) {
            Optional<Conversation> conversationOptional = conversationRepository.findConversation(request.getSenderId(), request.getReceiverId());
            if (conversationOptional.isEmpty()) {
                Conversation conversationEntity = Conversation.builder()
                        .createdBy(request.getSenderId())
                        .chatMembers(new ArrayList<>())
                        .build();
                ChatMember sender = ChatMember.builder()
                        .user(User.builder()
                                .id(request.getSenderId())
                                .build())
                        .conversation(conversationEntity)
                        .build();
                ChatMember receiver = ChatMember.builder()
                        .user(User.builder()
                                .id(request.getReceiverId())
                                .build())
                        .conversation(conversationEntity)
                        .build();
                conversationEntity.setChatMembers(List.of(sender, receiver));
                conversation = conversationRepository.save(conversationEntity);

            } else {
                conversation = conversationOptional.get();
            }
        } else if (request.getChatType() == ChatType.GROUP) {
            conversation = conversationRepository.findById(Long.parseLong(request.getReceiverId()))
                    .orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));
        } else {
            throw new RuntimeException("Chat type not supported");
        }

        Message message = messageMapper.toMessage(request);
        message.setConversation(conversation);
        message.setMessageStatus(MessageStatus.builder()
                .status(Status.SENT)
                .message(message)
                .build());

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

    public PageResponse<MessageResponse> getAllMessages(int page, int size, ChatType chatType, String senderId, String receiverId) {
        Conversation conversation;
        if (chatType == ChatType.PERSONAL) {
            Optional<Conversation> conversationOptional = conversationRepository.findConversation(senderId, receiverId);
            if (conversationOptional.isEmpty()) {
                return buildPageEmpty();
            }
            conversation = conversationOptional.get();
        }else{
            conversation =conversationRepository.findById(Long.parseLong(receiverId)).orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND));
        }
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Message> pageData = messageRepository.findMessagesByConversation(conversation.getId(), pageable);

        return PageResponse.<MessageResponse>builder()
                .currentPage(page)
                .totalPages(pageData.getTotalPages())
                .pageSize(size)
                .totalElements(pageData.getTotalElements())
                .data(pageData.getContent().stream().map(messageMapper::toMessageResponse).toList())
                .build();
    }


    private PageResponse<MessageResponse> buildPageEmpty(){
        return PageResponse.<MessageResponse>builder()
                .currentPage(1)
                .totalPages(1)
                .pageSize(15)
                .totalElements(0)
                .data(new ArrayList<>())
                .build();
    }
}
