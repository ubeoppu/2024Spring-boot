package com.kr.solo.repository;

import com.kr.solo.constant.Role;
import com.kr.solo.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;

@SpringBootTest
@Log4j2
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    EntityManager em;

    @Test
    public void insertMemberTest(){
        Member member= Member.builder()
                .email("test1234")
                .password(passwordEncoder.encode("1234"))
                .name("관리자")
                .role(Role.ADMIN)
                .phone("010-1111-1111")
                .build();

        memberRepository.save(member);
    }

}
