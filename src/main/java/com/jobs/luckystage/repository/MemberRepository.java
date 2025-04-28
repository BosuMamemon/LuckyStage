package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface MemberRepository extends JpaRepository<Members, String> {
    Members findByUsername(String username);
}
