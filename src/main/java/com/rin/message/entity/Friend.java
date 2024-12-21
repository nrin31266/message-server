package com.rin.message.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rin.message.constant.FriendshipStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "friend", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"sender_id", "receiver_id"})
})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Người gửi lời mời kết bạn
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    // Người nhận lời mời kết bạn
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    // Trạng thái kết bạn
    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    // Ngày gửi lời mời kết bạn
    private Instant sentAt;

    // Ngày chấp nhận kết bạn (nếu có)
    private Instant acceptedAt;

    // Ngày từ chối (nếu có)
    private Instant rejectedAt;

}
