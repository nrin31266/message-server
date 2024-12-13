package com.rin.message.repository;

import com.rin.message.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, String> {
    @Query("SELECT p FROM Profile p WHERE p.userId.id = :userId")
    Optional<Profile> findByUserId(@Param("userId") String userId);
}
