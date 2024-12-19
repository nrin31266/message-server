package com.rin.message.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(unique = true, nullable = false)
    String username;
    String password;
    @Column(unique = true, nullable = false)
    String email;
    boolean enabled;
    @Column(updatable = false)
    Instant createdAt;
    Instant updatedAt;
    @ManyToMany
    List<Role> roles;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", nullable = false, referencedColumnName = "id")
    Profile profile;
    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
