package com.nellchat.ncproject.config;

import com.nellchat.ncproject.user.repository.JpaUserRepository;
import com.nellchat.ncproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class RepositoryConfig {

    private final EntityManager em;

    @Bean
    public UserRepository userRepository(){
        return new JpaUserRepository(em);
    }

}
