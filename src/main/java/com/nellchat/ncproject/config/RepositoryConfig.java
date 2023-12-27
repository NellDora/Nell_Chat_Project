package com.nellchat.ncproject.config;

import com.nellchat.ncproject.member.repository.JpaMemberRepository;
import com.nellchat.ncproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class RepositoryConfig {

    private final EntityManager em;

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }

}
