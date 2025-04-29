package com.jobs.luckystage.controller;

import com.jobs.luckystage.repository.ConcertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/lottery")
@RequiredArgsConstructor
@Log4j2
public class LotteryController {

    private final ConcertRepository concertRepository;

    @PostMapping("/lottery")
    public String reserveLottery(@RequestParam("selectedDate") String selectedDate, Model model) {
        if (selectedDate == null || selectedDate.isEmpty()) {
            selectedDate = "2025-05-05"; // 안전용 기본값
        }
        model.addAttribute("selectedDate", "\"" + selectedDate + "\""); // 반드시 따옴표 붙여야 JS 문자열로 넘어감
        return "lottery/lottery";
    }
}
