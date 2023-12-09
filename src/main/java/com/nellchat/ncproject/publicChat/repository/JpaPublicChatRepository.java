package com.nellchat.ncproject.publicChat.repository;

import com.nellchat.ncproject.publicChat.domain.PublicChat;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Slf4j
@Repository
public class JpaPublicChatRepository implements  PublicChatRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaPublicChatRepository(EntityManager em){
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }


    @Override
    public void save(PublicChat publicChat) {
        em.persist(publicChat);
    }

}


