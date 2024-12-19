package com.rin.message.mapper;

import com.rin.message.dto.request.ChatMessageRequest;
import com.rin.message.dto.response.MessageResponse;
import com.rin.message.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "attachments", ignore = true)
    Message toMessage(ChatMessageRequest request);

    MessageResponse toMessageResponse(Message message);
}
