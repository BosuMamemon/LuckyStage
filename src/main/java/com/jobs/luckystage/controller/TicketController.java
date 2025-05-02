package com.jobs.luckystage.controller;

import com.jobs.luckystage.config.auth.PrincipalDetails;
import com.jobs.luckystage.domain.Concerts;
import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.domain.Tickets;
import com.jobs.luckystage.repository.ConcertRepository;
import com.jobs.luckystage.repository.MemberRepository;
import com.jobs.luckystage.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/ticket")
@RequiredArgsConstructor
@Log4j2
public class TicketController {

    private final ConcertRepository concertsRepository;
    private final TicketRepository ticketRepository;
    private final MemberRepository memberRepository;


    @PostMapping("/complete")
    public String reserveTicket(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                @RequestParam("concertNum") Long concertNum,
                                @RequestParam("selectedDate") String selectedDateStr,
                                Model model, RedirectAttributes redirectAttributes) {

        // 로그인 체크
        if (principalDetails == null || principalDetails.getUsername() == null) {
            return "redirect:/member/login";
        }

        // 콘서트 정보 조회
        Concerts concert = concertsRepository.findById(concertNum)
                .orElseThrow(() -> new IllegalArgumentException("공연 정보 없음"));

        // 예약 정보 생성
        Members member = memberRepository.findByUsername(principalDetails.getUsername());

        // 중복 예약 체크
        boolean alreadyReserved = ticketRepository.existsByMembersAndConcerts(member, concert);
        if (alreadyReserved) {
            redirectAttributes.addFlashAttribute("duplicateReservation", true);
            return "redirect:/mypage/reservation"; // 팝업 띄우고 예약내역으로
        }
        // 날짜 파싱
        LocalDateTime selectedDate = LocalDate.parse(selectedDateStr).atStartOfDay();

        // 추첨일 계산 (공연일 3일 전)
        Date lotteryDate = java.sql.Date.valueOf(LocalDate.parse(selectedDateStr).minusDays(3));

        //신규 티켓 생성
        Tickets ticket = new Tickets();
        ticket.setConcerts(concert); // 공연 정보
        ticket.setMembers(member);   // 회원 정보
        ticket.setSelectedDate(selectedDate); // 관람 날짜
        ticket.setLotteryDate(lotteryDate);   // 추첨 날짜

        Tickets savedTicket = ticketRepository.save(ticket);
        System.out.println("concerts null? " + (savedTicket.getConcerts() == null));
        // 모델에 담아서 넘기기
        model.addAttribute("ticket", savedTicket);

        return "ticket/complete"; // templates/ticket/complete.html로 이동
    }


}