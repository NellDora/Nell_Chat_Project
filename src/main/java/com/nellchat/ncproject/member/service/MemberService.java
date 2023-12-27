package com.nellchat.ncproject.member.service;

import com.nellchat.ncproject.member.domain.Member;
import com.nellchat.ncproject.member.dto.MemberDTO;
import com.nellchat.ncproject.member.exception.IdDuplicationException;
import com.nellchat.ncproject.member.exception.PasswordCheckFailException;
import com.nellchat.ncproject.member.repository.MemberRepository;
import com.nellchat.ncproject.member.vo.JoinResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(Member member){
        memberRepository.save(member);
    }

    public void updateInfo(Member member){
        Member findMember = memberRepository.findByNumber(member.getNumber());
        findMember.updateInfo(member.getMemberName(), member.getMemberNickname(), member.getEmail());

    }

    public Member findByNumber(Long num){
        return memberRepository.findByNumber(num);

    }

    public Member findById(String Id){
        return  memberRepository.findById(Id);
    }

    public JoinResult save(MemberDTO memberDTO){
        JoinResult joinResult = JoinResult.STANDBY;
        try {
            duplicateCheckId(memberDTO.getMemberId());
            //아이디 중복 여부에 따라 유저 회원가입이 될지 안될지

            joinPasswordCheck(memberDTO.getPasswordOne(), memberDTO.getPasswordTwo());

            log.info("UserService Save : 입력받은 값 = {}", memberDTO.toString());
            memberRepository.save(Member.createUser(memberDTO.getMemberId(),passwordEncoder.encode(memberDTO.getPasswordOne()), memberDTO.getMemberName(), memberDTO.getMemberNickname(), memberDTO.getEmail()));
            joinResult = JoinResult.SUCCESS;
        } catch (IdDuplicationException e) {
            log.info("{}",e);
            joinResult = JoinResult.ID_EEROR;
        }catch (PasswordCheckFailException e){
            log.info("{}",e);
            joinResult=JoinResult.PASSWORD_ERROR;
        }
        return joinResult;
    }

    //아이디 중복 검증
    public void duplicateCheckId(String id) throws IdDuplicationException {
        Member findUsers = null;
        try{
            findUsers = memberRepository.findById(id);
        }catch (NullPointerException e){
            log.info("중복 발생 에러가 발생한 것인가");
        }finally {
            if(findUsers!=null) {
                throw new IdDuplicationException("이미 존재하는 아이디 입니다.");
            }else{
                log.info("UserService : 중복 검사 통과");
            }
        }

    }

    //회원가입시 비밀번호 이중체크
    public void joinPasswordCheck(String passwordOne , String passwordTwo) throws PasswordCheckFailException {
        if(passwordOne.equals(passwordTwo)){
            log.info("UserService : 비밀번호 체크 성공");
        }else{
            throw new PasswordCheckFailException("비밀번호가 맞지 않습니다.");
        }
    }

    //패스워드 업데이트
    public void passwordUpdate(Long number,String password){
        memberRepository.updatePassword(number, passwordEncoder.encode(password));
    }

}
