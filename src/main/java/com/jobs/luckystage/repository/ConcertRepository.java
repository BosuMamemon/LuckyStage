package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Concerts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concerts, Long> {
}
