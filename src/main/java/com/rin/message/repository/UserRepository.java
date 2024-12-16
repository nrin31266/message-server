package com.rin.message.repository;

import com.rin.message.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);

    @Query("""
                SELECT u 
                FROM User u 
                LEFT JOIN u.profile p
                WHERE LOWER(u.username) LIKE CONCAT('%', :keyword, '%') 
                   OR LOWER(CONCAT(p.firstName, ' ', p.lastName)) LIKE CONCAT('%', :keyword, '%')
            """)
    Page<User> search(@Param("keyword") String keyword, Pageable pageable);


}
