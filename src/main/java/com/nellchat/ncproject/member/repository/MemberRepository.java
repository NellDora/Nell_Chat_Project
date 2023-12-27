package com.nellchat.ncproject.member.repository;

import com.nellchat.ncproject.member.domain.Member;

public interface MemberRepository {

    public void save(Member member);

    public Member findByNumber(Long num);

    public Member findById(String id);

    public void updatePassword(Long number ,String password);
}
