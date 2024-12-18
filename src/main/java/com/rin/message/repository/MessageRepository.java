package com.rin.message.repository;

import com.rin.message.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Long> {


    @Query("""
                select m from Message m
                join ChatMember cm1 on cm1.conversation.id = m.conversation.id
                join ChatMember cm2 on cm2.conversation.id = m.conversation.id
                where (cm1.userId = :senderId and cm2.userId = :receiverId)
                   or (cm1.userId = :receiverId and cm2.userId = :senderId)
            """)
    Page<Message> findMessages(@Param("senderId") String senderId, @Param("receiverId") String receiverId, Pageable pageable);

}
