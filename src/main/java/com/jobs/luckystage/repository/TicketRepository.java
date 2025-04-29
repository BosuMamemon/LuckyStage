package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Tickets, Long> {
    // 특별한 메소드 추가할 필요 없음! 기본 CRUD 다 제공됨
}
