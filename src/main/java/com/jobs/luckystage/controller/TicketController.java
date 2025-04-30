package com.jobs.luckystage.controller;

import com.jobs.luckystage.config.auth.PrincipalDetails;
import com.jobs.luckystage.domain.Members;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ticket")
@RequiredArgsConstructor
@Log4j2
public class TicketController {

    @PostMapping("/complete")
    public String reserveTicket(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        // 로그인 체크
        if (principalDetails == null || principalDetails.getUsername() == null) {
            return "redirect:/member/login";
        }

        // 로그인된 회원 정보만 가져옴 (사용은 안 함)
        Members member = principalDetails.getMember();

        // 바로 완료 페이지로 리다이렉트
        return "redirect:/ticket/complete";
    }
    @GetMapping("/complete")
    public String completeTicket() {
        return "ticket/complete";
    }
}