package com.jobs.luckystage.controller;

import com.jobs.luckystage.dto.ConcertDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.ReviewDTO;
import com.jobs.luckystage.service.ConcertService;
import com.jobs.luckystage.service.ReviewService;
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
    private final ReviewService reviewService;

    @GetMapping("/")
    public String home(Model model) {
        PageRequestDTO bestDTO = new PageRequestDTO();
        PageRequestDTO latestDTO = new PageRequestDTO();
        bestDTO.setType("rating");
        latestDTO.setType("startDate");
        List<ConcertDTO> bestList = concertService.list(bestDTO);
        List<ConcertDTO> latestList = concertService.list(latestDTO);
        List<ReviewDTO> latestReviewList = reviewService.getAllReviews(new PageRequestDTO()).getDtoList();
//        log.info("latestReviewList", latestReviewList.get(0));
        model.addAttribute("bestList", bestList);
        model.addAttribute("latestList", latestList);
        model.addAttribute("latestReviewList", latestReviewList);
        return "/home";
    }
}
