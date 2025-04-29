package com.jobs.luckystage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lottery")
@RequiredArgsConstructor
@Log4j2
public class LotteryController {

    @PostMapping("/lottery")
    public String reserveLottery(@RequestParam("selectedDate") String selectedDate, Model model) {
        model.addAttribute("selectedDate", "\"" + selectedDate + "\""); // 따옴표 넣어야 JS에서 문자열로 인식
        return "lottery/lottery"; // -> lottery.html로 이동
    }
}
