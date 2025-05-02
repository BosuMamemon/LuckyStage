package com.jobs.luckystage.controller;

import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class AdminMemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public String listMembers(Model model,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "username") String sort) {

        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(sort).ascending());
        Page<Members> memberPage = memberService.getAllMembers(pageable);

        model.addAttribute("memberList", memberPage.getContent());
        model.addAttribute("page", page);
        model.addAttribute("totalPages", memberPage.getTotalPages());
        model.addAttribute("sort", sort);

        return "admin/admin";
    }

    @PostMapping("/deleteMember")
    public String deleteMember(@RequestParam("username") String username) {
        memberService.deleteMember(username);
        return "redirect:/admin/members";
    }

}
