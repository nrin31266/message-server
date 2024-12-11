package com.rin.message.config;

import com.rin.message.entity.User;
import com.rin.message.repository.RoleRepository;
import com.rin.message.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class AppInitConfig {
    final PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner init(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            log.warn("Hello Nguyen Van Rin");
            if (userRepository.findByUsername("admin").isEmpty()) {
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roleRepository.findAllById(List.of("ADMIN")))
                        .build();
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
        };
    }
}
