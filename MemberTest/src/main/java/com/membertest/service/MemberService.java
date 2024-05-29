package com.membertest.service;

import com.membertest.entity.Member;
import com.membertest.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@Getter
@RequiredArgsConstructor
@ToString
@Log4j2
public class MemberService {

    private final MemberRepository memberRepository;

    //CRUD -> insert, update
    public void register(Member member){
        log.info("register...");

        memberRepository.save(member);

    }

    //CRUD -> update
    public void delete(Long member_id){
        log.info("delete...");
        memberRepository.deleteById(member_id);
    }

    //CRUD -> read
    public Member readOne(Long member_id){
        log.info("readOne...");
        return memberRepository.findById(member_id).orElseThrow(() -> new EntityNotFoundException("member not found"));
    }
    //CRUD -> readAll
    public List<Member> readAll(){
        log.info("readAll...");
        return memberRepository.findAll();
    }

    //Paging
    public Page<Member> readPaging(Pageable pageable){
        log.info("readPaging...");
        return memberRepository.findAll(pageable);
    }


}
