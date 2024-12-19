package com.rin.message.controller;

import com.rin.message.dto.ApiResponse;
import com.rin.message.dto.request.ChatMessageRequest;
import com.rin.message.dto.response.MessageResponse;
import com.rin.message.service.ChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
public class MessageController {
    ChatService chatService;

    @PostMapping
    ApiResponse<MessageResponse> create(@RequestBody ChatMessageRequest chatMessageRequest
            , @RequestParam(value = "isGroup", defaultValue = "false", required = false) boolean isGroup) {
        return ApiResponse.<MessageResponse>builder()
                .result(chatService.saveMessage(chatMessageRequest, isGroup))
                .build();
    }

    @GetMapping
    ApiResponse<List<MessageResponse>> getAll() {
        return ApiResponse.<List<MessageResponse>>builder()
                .result(chatService.getAllMessages())
                .build();
    }
}
