package com.rin.message.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
    @JsonBackReference
    User user;
    String firstName;
    String lastName;
    LocalDate dob;
    String gender;
    String phone;
    String avatar;
    @Column(updatable = false)
    Instant createdAt;
    Instant updatedAt;
    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

}