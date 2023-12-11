package com.nellchat.ncproject.publicChat.repository;

import com.nellchat.ncproject.publicChat.domain.PublicChat;
import com.nellchat.ncproject.publicChat.domain.QPublicChat;
import com.nellchat.ncproject.publicChat.domain.QPublicChatRoom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

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

    @Override
    public List<PublicChat> findByChatroomId(Long id) {

        QPublicChat publicChat = QPublicChat.publicChat;
        QPublicChatRoom publicChatRoom = QPublicChatRoom.publicChatRoom;
        List<PublicChat> publicChats =query.select(publicChat).from(publicChat).join(publicChat.publicChatRoom, publicChatRoom)
                .where(publicChat.publicChatRoom.id.eq(id)).fetch();
        return publicChats;
    }
}


