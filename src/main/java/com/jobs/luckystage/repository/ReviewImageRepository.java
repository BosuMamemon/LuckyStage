package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.ReviewImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImages, Long> {
    List<ReviewImages> findByReviewsReviewNum(Long reviewNum);
}
