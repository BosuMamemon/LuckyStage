package com.jobs.luckystage.controller;

import com.jobs.luckystage.dto.ReviewDTO;
import com.jobs.luckystage.service.ConcertService;
import com.jobs.luckystage.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ConcertService concertService;

    @GetMapping("/list")
    public void getList(Model model) {
        List<ReviewDTO> reviewList = reviewService.getAllReviews();
        model.addAttribute("reviewList", reviewList);
    }
    @GetMapping("/register")
    public void getRegister(@RequestParam("concertNum") long concertNum, Model model) {
        String title = concertService.findById(concertNum).getTitle();
        model.addAttribute("title", title);
        model.addAttribute("concertNum", concertNum);
    }
}
