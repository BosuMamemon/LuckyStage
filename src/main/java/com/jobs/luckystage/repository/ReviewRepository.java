package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> findAllByOrderByReviewNumDesc();
}
