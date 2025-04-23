package com.jobs.luckystage.service;

import com.jobs.luckystage.dto.ReviewsDTO;

import java.util.List;

public interface ReviewsService {
    ReviewsDTO saveReview(ReviewsDTO reviewsDTO);
    ReviewsDTO getReview(Long reviewNum);
    List<ReviewsDTO> getAllReviews();
    ReviewsDTO updateReview(Long reviewNum, ReviewsDTO reviewsDTO);
    void deleteReview(Long reviewNum);
}
