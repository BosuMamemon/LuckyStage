package com.jobs.luckystage.controller;

import com.jobs.luckystage.dto.ConcertDTO;
import com.jobs.luckystage.service.ConcertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class HomeController {
    private final ConcertService concertService;

    @GetMapping("/")
    public String home(Model model) {
        List<ConcertDTO> bestList = concertService.list("rating");
        List<ConcertDTO> latestList = concertService.list("startDate");
        model.addAttribute("bestList", bestList);
        model.addAttribute("latestList", latestList);
        return "/home";
    }
}
