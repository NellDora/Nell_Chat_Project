package com.nellchat.ncproject.user.repository;

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
public class JpaUserRepository implements UserRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaUserRepository (EntityManager em){
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }


    @Override
    public void save(User user) {
        em.persist(user);
        log.info("신규 유저 등록 완료");
    }

    @Override
    public User findByNumber(Long num) {
        return em.find(User.class, num);
    }

    @Override
    public User findById(String id) {
        QUser user = QUser.user;
        return query.select(user).from(user).where(user.userId.eq(id)).fetchOne();
    }


    @Override
    public void updatePassword(Long number, String password) {
        User findUser = findByNumber(number);
        findUser.updatePassword(password);
    }
}
