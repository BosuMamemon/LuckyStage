package com.jobs.luckystage.controller;

import com.jobs.luckystage.config.auth.PrincipalDetails;
import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.repository.MemberRepository;
import com.jobs.luckystage.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Member;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Log4j2
public class MypageController {

    private final MemberRepository memberRepository;

    @GetMapping("/mypage")
    public void mypagePage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        String username = principalDetails.getUsername();  // PrincipalDetails에서 username 가져오기
        Members member = memberRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        model.addAttribute("member", member);
    }
    //회원정보 수정
    @PostMapping("/info")
    public String updateInfo(@AuthenticationPrincipal PrincipalDetails principalDetails,
                             @ModelAttribute Members updateMemberData) {
        String username = principalDetails.getUsername();
        Members member = memberRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        member.setNickname(updateMemberData.getNickname());
        member.setTel(updateMemberData.getTel());
        member.setEmail(updateMemberData.getEmail());
        member.setAddress(updateMemberData.getAddress());

        memberRepository.save(member);

        return "redirect:/mypage/mypage?success=update"; // ⭐ success 파라미터 넘김
    }
    //회원탈퇴
    private final MemberService memberService;

    @GetMapping("/deleteMember")
    public String deleteMember(@AuthenticationPrincipal PrincipalDetails principalDetails,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        String username = principalDetails.getUsername();
        Members member = memberRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        memberService.deleteMember(username); // 리뷰 username NULL 처리 + 회원 삭제

        // 세션 로그아웃
        new SecurityContextLogoutHandler().logout(request, response, null);

        return "redirect:/?success=delete";
    }
    //예약내역
    @GetMapping("/reservation")
    public void reservationPage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        String username = principalDetails.getUsername();
        // 필요하면 예약 목록 조회해서 model.addAttribute 해도 됨
        log.info("Reservation page for user: " + username);
    }

    //찜목록 페이지
    @GetMapping("/pick")
    public void pickPage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        String username = principalDetails.getUsername();
        // 필요하면 찜 목록 조회해서 model.addAttribute 해도 됨
        log.info("Pick page for user: " + username);
    }
}

