package com.jobs.luckystage.controller;

import com.jobs.luckystage.domain.Tickets;
import com.jobs.luckystage.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ticket")
@RequiredArgsConstructor
@Log4j2
public class TicketController {

    private final TicketRepository ticketRepository;

    @GetMapping("/complete")
    public void reserveLottery(@RequestParam("selectedDate") String selectedDate, Model model) {
        model.addAttribute("selectedDate", selectedDate); // 따옴표 넣어야 JS에서 문자열로 인식
    }

    @PostMapping("/complete")
    public String completeReservation(@ModelAttribute Tickets ticket, Model model) {
        Tickets savedTicket = ticketRepository.save(ticket);
        model.addAttribute("ticket", savedTicket);
        return "redirect:/mypage/reservation";
    }
}
