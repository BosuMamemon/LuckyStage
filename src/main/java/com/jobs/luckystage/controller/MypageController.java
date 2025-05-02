package com.jobs.luckystage.controller;

import com.jobs.luckystage.config.auth.PrincipalDetails;
import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.domain.Tickets;
import com.jobs.luckystage.dto.ConcertDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.dto.ReviewDTO;
import com.jobs.luckystage.repository.MemberRepository;
import com.jobs.luckystage.repository.TicketRepository;
import com.jobs.luckystage.service.ConcertService;
import com.jobs.luckystage.service.MemberService;
import com.jobs.luckystage.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Log4j2
public class MypageController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final TicketRepository ticketRepository;
    private final ConcertService concertService;
    private final ReviewService reviewService;

    @GetMapping("/mypage")
    public String mypagePage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        if (principalDetails == null) {
            return "redirect:/member/login";
        }
        String username = principalDetails.getUsername();
        Members member = memberRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        model.addAttribute("member", member);
        return "mypage/mypage";
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

    @PostMapping("/deleteMember")
    @ResponseBody
    public ResponseEntity<String> deleteMemberAjax(
            @RequestBody Map<String, String> body,
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            HttpServletRequest request,
            HttpServletResponse response) {

        String inputPw = body.get("password");
        String username = principalDetails.getUsername();

        Members member = memberRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        // 비밀번호 일치 확인
        if (!passwordEncoder.matches(inputPw, member.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 일치하지 않습니다.");
        }

        // 리뷰 null 처리 + 회원 삭제
        memberService.deleteMember(username);

        // 로그아웃 처리
        new SecurityContextLogoutHandler().logout(request, response, null);

        return ResponseEntity.ok("탈퇴 완료");
    }

    //예약내역
    @GetMapping("/reservation")
    public String reservationPage(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                  Model model) {
        if (principalDetails == null) {
            return "redirect:/member/login";
        }

        String username = principalDetails.getUsername();
        Members member = memberRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        List<Tickets> tickets = ticketRepository.findByMembers(member);
        model.addAttribute("tickets", tickets);

        // FlashAttribute는 모델에서 자동으로 접근 가능함 (추가 작업 X)
        return "mypage/reservation";
    }

    //찜목록 페이지
    @GetMapping("/pick")
    public void pickPage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        String username = principalDetails.getUsername();
        List<ConcertDTO> dtoList = concertService.findBookmark(username);
        model.addAttribute("dtoList", dtoList);
    }

    //추첨결과 확인란
    @GetMapping("/lottery")
    public String lotteryPage(@RequestParam("ticketId") Long ticketId, Model model) {
        Tickets ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("티켓 정보를 찾을 수 없습니다."));
        model.addAttribute("ticket", ticket);
        return "mypage/lottery";
    }

//    내가 쓴 리뷰
    @GetMapping("/review")
    public void getReview(@AuthenticationPrincipal PrincipalDetails principalDetails, PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setSize(8);
        PageResponseDTO<ReviewDTO> reviewList = reviewService.getAllReviewsByUsername(pageRequestDTO, principalDetails.getUsername());
        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("reviewList", reviewList);
    }
}

