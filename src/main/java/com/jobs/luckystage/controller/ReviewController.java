package com.jobs.luckystage.controller;

import com.jobs.luckystage.dto.ReviewDTO;
import com.jobs.luckystage.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public String ReviewList(Model model) {
        List<ReviewDTO> reviewList = reviewService.getAllReviews();
        model.addAttribute("reviewList", reviewList);
        return "/reviews/reviews"; // templates/reviews/reviews.html
    }
    @GetMapping("/reviews/reviewform")
    public String reviewForm() {
        return "/reviews/reviewform"; // 확장자 .html은 생략
    }
}
