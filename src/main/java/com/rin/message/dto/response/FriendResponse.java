
package com.rin.message.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendResponse {
    String friendId;
    String sender;
    String receiver;
    Instant sentAt;
    Instant acceptedAt;
    Instant rejectedAt;
}
