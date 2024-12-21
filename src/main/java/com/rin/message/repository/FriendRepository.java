package com.rin.message.repository;

import com.rin.message.constant.FriendshipStatus;
import com.rin.message.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("""
                select f from Friend f
                join f.sender u1
                join f.receiver u2
                where f.sender.id = :senderId and f.receiver.id = :receiverId
                 and f.status = :status
            """)
    Optional<Friend> findFriendStatus(@Param("senderId") String senderId, @Param("receiverId") String receiverId,  @Param("status") FriendshipStatus status);

}
