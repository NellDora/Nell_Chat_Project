package com.nellchat.ncproject.publicChat.repository;


import com.nellchat.ncproject.publicChat.domain.PublicChatRoom;
import com.nellchat.ncproject.publicChat.domain.QPublicChatRoom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@Slf4j
public class JpaPublicChatRoomRepository implements PublicChatRoomRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaPublicChatRoomRepository(EntityManager em){
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public void save(PublicChatRoom publicChatRoom) {
        em.persist(publicChatRoom);
    }

    @Override
    public PublicChatRoom findById(Long id) {
        return em.find(PublicChatRoom.class, id);
    }

    @Override
    public PublicChatRoom findByRoomCode(String roomCode) {
        QPublicChatRoom publicChatRoom = QPublicChatRoom.publicChatRoom;
        return query.select(publicChatRoom).from(publicChatRoom).where(publicChatRoom.roomCode.eq(roomCode)).fetchOne();
    }


    @Override
    public List<PublicChatRoom> findByAll() {
        QPublicChatRoom publicChatRoom = QPublicChatRoom.publicChatRoom;

        List<PublicChatRoom> publicChatRooms =
                query.select(publicChatRoom).from(publicChatRoom).fetch();
        return publicChatRooms;
    }

    @Override
    public List<PublicChatRoom> findByAllWithPaging(int start, int limit) {

        QPublicChatRoom publicChatRoom = QPublicChatRoom.publicChatRoom;

        List<PublicChatRoom> publicChatRooms =
                query.select(publicChatRoom).from(publicChatRoom).offset(start).limit(limit).fetch();
        return publicChatRooms;
    }
}

