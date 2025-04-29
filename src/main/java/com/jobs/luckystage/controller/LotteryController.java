package com.jobs.luckystage.controller;

import com.jobs.luckystage.dto.TicketDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/lottery")
@RequiredArgsConstructor
@Log4j2
public class LotteryController {

    @GetMapping("/lottery")
    public void reserveLottery(@RequestParam("selectedDate") String selectedDate, Model model) {

        model.addAttribute("selectedDate", selectedDate); // 따옴표 넣어야 JS에서 문자열로 인식
    }
}
