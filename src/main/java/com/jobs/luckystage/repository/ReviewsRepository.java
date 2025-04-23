package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
}
