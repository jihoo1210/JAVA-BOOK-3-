package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members/new")
    public String newMember() {
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm form) {
        // System.out.println(form);
        log.info(form.toString());
        // 1. Dto -> Entity
        Member member = form.toEntity();
        // System.out.println(member);
        log.info(member.toString());
        // 2. Repository로 Entity 저장
        Member saved = memberRepository.save(member);
        // System.out.println(saved);
        log.info(saved.toString());
        return "members/";
    }
}
