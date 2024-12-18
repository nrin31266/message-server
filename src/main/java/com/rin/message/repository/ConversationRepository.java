package com.rin.message.repository;


import com.rin.message.entity.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("""
                select c from Conversation c
                join ChatMember cm1 on cm1.conversation.id = c.id
                join ChatMember cm2 on cm2.conversation.id = c.id
                where (cm1.userId = :senderId and cm2.userId = :receiverId)
                or (cm1.userId = :receiverId and cm2.userId = :senderId)
            """)
    Optional<Conversation> findConversation(@Param("senderId") String senderId, @Param("receiverId") String receiverId);


}
