package com.nellchat.ncproject.publicChat.repository;


import com.nellchat.ncproject.publicChat.domain.PublicChatRoom;
import com.nellchat.ncproject.publicChat.domain.PublicChatUser;
import com.nellchat.ncproject.publicChat.domain.QPublicChatRoom;
import com.nellchat.ncproject.publicChat.domain.QPublicChatUser;
import com.nellchat.ncproject.user.domain.QUser;
import com.nellchat.ncproject.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
@Slf4j
public class JpaPublicChatUserRepository implements PublicChatUserRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaPublicChatUserRepository(EntityManager em){
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public void save(PublicChatUser publicChatUser) {
        em.persist(publicChatUser);
    }

    @Override
    public PublicChatUser findById(Long id) {
        return em.find(PublicChatUser.class, id);
    }

    @Override
    public PublicChatUser findByUserAndPublicChatRoom(User inputUser, PublicChatRoom inputPublicChatRoom) {
        QPublicChatUser publicChatUser = QPublicChatUser.publicChatUser;
        QUser user = QUser.user;
        QPublicChatRoom publicChatRoom = QPublicChatRoom.publicChatRoom;
        return query.select(publicChatUser).from(publicChatUser).join(publicChatUser.user, user)
                .on(publicChatUser.user.number.eq(user.number))
                .where(publicChatUser.user.number.eq(inputUser.getNumber()).and(publicChatUser.chatRoom.id.eq(inputPublicChatRoom.getId())))
                .fetchOne();
    }
}
