package com.jobs.luckystage.service;

import com.jobs.luckystage.dto.ReviewCommentDTO;
import com.jobs.luckystage.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    void saveReview(ReviewDTO reviewDTO);
    List<ReviewDTO> getAllReviews();
    ReviewDTO getReview(Long reviewNum);
    void deleteReview(Long reviewNum);
    void addComment(ReviewCommentDTO commentDTO);
    void deleteComment(Long commentId);
}