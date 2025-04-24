package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.ConcertImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConcertImageRepository extends JpaRepository<ConcertImages, Long> {
    Optional<ConcertImages> findByConcerts_ConcertNum(long concertsConcertNum);
}
