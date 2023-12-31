package com.nellchat.ncproject.member.repository;

import com.nellchat.ncproject.member.domain.Member;
import com.nellchat.ncproject.member.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
@Slf4j
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }


    @Override
    public void save(Member member) {
        em.persist(member);
        log.info("신규 유저 등록 완료");
    }

    @Override
    public Member findByNumber(Long num) {
        return em.find(Member.class, num);
    }

    @Override
    public Member findById(String id) {
        log.info("@@@@@@@@@@ MemberDAO findById 전달받은 id값 {} @@@@@@@@@@@", id);
        QMember member = QMember.member;
        return query.select(member).from(member).where(member.memberId.eq(id)).fetchOne();

    }


    @Override
    public void updatePassword(Long number, String password) {
        Member findMember = findByNumber(number);
        findMember.updatePassword(password);
    }
}
