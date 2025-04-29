package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Reviews, Long> {
    @Query("SELECT AVG(r.rating) FROM Reviews r JOIN r.concerts c GROUP BY c.concertNum HAVING c.concertNum = :concertsConcertNum")
    long avgRatingByConcerts_concertNum(long concertsConcertNum);
}
