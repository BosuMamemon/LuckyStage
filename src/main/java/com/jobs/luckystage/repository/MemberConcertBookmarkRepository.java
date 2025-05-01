package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.MemberConcertBookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberConcertBookmarkRepository extends JpaRepository<MemberConcertBookmark, Long> {
    List<MemberConcertBookmark> findAllByMembers_Username(String membersUsername);
    void deleteByConcerts_ConcertNumAndMembers_Username(long concertsConcertNum, String membersUsername);

    void deleteAllByMembers_Username(String membersUsername);
}
