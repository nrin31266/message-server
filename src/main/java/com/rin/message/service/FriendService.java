package com.rin.message.service;

import com.rin.message.constant.FriendshipStatus;
import com.rin.message.dto.request.FriendRequest;
import com.rin.message.entity.Friend;
import com.rin.message.entity.User;
import com.rin.message.repository.FriendRepository;
import com.rin.message.repository.ProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FriendService {
    FriendRepository friendRepository;

    public Object send(FriendRequest friendRequest) {
        Friend friend = Friend.builder()
                .sender(User.builder()
                        .id(friendRequest.getSender())
                        .build())
                .receiver(User.builder()
                        .id(friendRequest.getReceiver())
                        .build())
                .sentAt(Instant.now())
                .status(FriendshipStatus.PENDING)
                .build();

        friendRepository.save(friend);
        return null;
    }

    public Object accept(FriendRequest friendRequest) {
        Friend friend = friendRepository.findFriendStatus(friendRequest.getSender(), friendRequest.getReceiver(), FriendshipStatus.PENDING).orElseThrow(()->new RuntimeException("Can't find friend request"));
        friend.setStatus(FriendshipStatus.ACCEPTED);
        friend.setAcceptedAt(Instant.now());
        friendRepository.save(friend);
        return null;
    }

    public Object reject(FriendRequest friendRequest) {
        Friend friend = friendRepository.findFriendStatus(friendRequest.getSender(), friendRequest.getReceiver(), FriendshipStatus.PENDING).orElseThrow(()->new RuntimeException("Can't find friend request"));
        friend.setStatus(FriendshipStatus.REJECTED);
        friend.setRejectedAt(Instant.now());
        friendRepository.save(friend);
        return null;
    }

    public Object cancel(FriendRequest friendRequest) {
        Friend friend = friendRepository.findFriendStatus(friendRequest.getSender(), friendRequest.getReceiver(), FriendshipStatus.ACCEPTED).orElseThrow(()->new RuntimeException("Can't find friend request"));
        friend.setStatus(FriendshipStatus.ACCEPTED);
        friendRepository.save(friend);
        return null;
    }

    public

}
