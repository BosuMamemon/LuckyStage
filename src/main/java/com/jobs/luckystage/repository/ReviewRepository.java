package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Reviews, Long> {

    // 공연별 평균 평점 조회
    @Query("SELECT AVG(r.rating) FROM Reviews r JOIN r.concerts c GROUP BY c.concertNum HAVING c.concertNum = :concertsConcertNum")
    Long avgRatingByConcerts_concertNum(long concertsConcertNum);  // long → **Long** 으로 바꿔줘 (NPE 방지)

    // 특정 유저가 작성한 리뷰 전체 조회
    List<Reviews> findAllByMembersUsername(String username);

    List<Reviews> findAllByConcerts_ConcertNum(long concertsConcertNum);
}
