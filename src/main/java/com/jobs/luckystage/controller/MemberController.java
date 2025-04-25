package com.jobs.luckystage.controller;

import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public void login() {}

    @GetMapping("/register")
    public void getRegister() {}

    @PostMapping("/register")
    public String postRegister(Members members) {
        String rawPassword = members.getPassword();
        String encryptedPassword = bCryptPasswordEncoder.encode(rawPassword);
        members.setPassword(encryptedPassword);
        members.setRole("USER");
        memberRepository.save(members);
        return "redirect:/member/login";
    }
}
