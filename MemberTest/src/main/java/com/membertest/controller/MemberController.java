package com.membertest.controller;

import com.membertest.entity.Member;
import com.membertest.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("members", members);
        return "member/list";
    }

    @GetMapping("/new")
    public String newGet(Model model) {
        model.addAttribute("member", new Member());
        return "member/newForm";
    }

    @PostMapping("/new")
    public String newPost(@ModelAttribute Member member, Model model) {
        log.info("new Post:" + member);

        memberService.register(member);

        return "redirect:/member/list";

    }

    @GetMapping("/edit/{id}")
    public String updateGet(@PathVariable("id") Long id, Model model) {
        model.addAttribute("member", memberService.readOne(id));
        return "member/updateForm";

    }

    @PostMapping("/edit")
    public String updatePost(@ModelAttribute Member member) {
        log.info("------------------------------");
        log.info("updatePost:" + member);
        memberService.register(member);
        return "redirect:/member/list";
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable("id")Long id){
        memberService.delete(id);
        return "redirect:/member/list";
    }

    //페이징 처리
    @GetMapping("/list2")//아무런 값도 없을 시 defaultValue
    public String listPage(@RequestParam(name="page", defaultValue = "0") int page,
                           @RequestParam(name="size", defaultValue = "5") int size,
                           Model model){
        log.info("작동 테스트");
        Pageable pageable = PageRequest.of(page, size);
        Page<Member> memberPage = memberService.readPaging(pageable);
        log.info("memberPage:"+memberPage);


        model.addAttribute("memberPage",memberPage);
        return "member/list2";
    }
}