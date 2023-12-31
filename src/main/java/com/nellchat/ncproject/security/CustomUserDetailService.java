package com.nellchat.ncproject.security;

import com.nellchat.ncproject.member.domain.Member;
import com.nellchat.ncproject.member.repository.MemberRepository;
import com.nellchat.ncproject.member.service.MemberService;
import com.nellchat.ncproject.security.domain.SecurityContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member findMember = memberRepository.findById(username);
        if(findMember == null){
            throw new UsernameNotFoundException("UsernameNotFountException");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        SecurityContext securityContext = new SecurityContext(findMember,roles);

        UserDetails userDetails = User.builder().username(findMember.getMemberId())
                .password(findMember.getPassword())
                .authorities("ROLE_USER")
                .build();
        return userDetails;
    }
}
