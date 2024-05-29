package com.membertest.controller;

import com.membertest.entity.Member;
import com.membertest.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/read/{id}")
    public String readOne(@PathVariable("id") Long id) {
        Member member = memberService.readOne(id);

        log.info("member:" + member);
        return null;
    }

    @GetMapping("/list")
    public String readList(Model model) {
        List<Member> members = memberService.readAll();
            members.forEach(list -> log.info(list));
            model.addAttribute("member", members);
            return "/member/list";
        }
    }

