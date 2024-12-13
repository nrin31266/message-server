package com.rin.message.controller;

import com.rin.message.dto.request.socket.ChatMessage;
import com.rin.message.dto.request.socket.UserRegistration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        log.info(chatMessage.toString());
        String receiver = chatMessage.getReceiver();
        if(receiver != null) {
            messagingTemplate.convertAndSendToUser(receiver,"/queue/message/", chatMessage);
        }
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload UserRegistration userRegistration, SimpMessageHeaderAccessor headerAccessor) {
        log.info(userRegistration.toString());
        headerAccessor.getSessionAttributes().put("user", userRegistration.getUsername());
    }
}
