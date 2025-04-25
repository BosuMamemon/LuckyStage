package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, String> {
    Members findByUsername(String username);
}
