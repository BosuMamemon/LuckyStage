package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.ReviewComments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewCommentRepository extends JpaRepository<ReviewComments, Long> {
    List<ReviewComments> findByReviewsReviewNum(Long reviewNum);
}
