package com.rin.message.repository;

import com.rin.message.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, String> {
    Optional<Profile> findByUserId(String userId);
}
