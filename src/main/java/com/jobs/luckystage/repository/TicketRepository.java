package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Concerts;
import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.domain.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Tickets, Long> {
    List<Tickets> findByMembers(Members member);

    boolean existsByMembersAndConcerts(Members members, Concerts concerts);
}
