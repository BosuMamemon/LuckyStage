package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface MemberRepository extends JpaRepository<Members, String> {
    @Query("select m from Members as m where m.name=:name")
    Members name(@RequestParam("name") String name);
    Members findByName(String name);
    Members findByEmail(String email);
    Members findByUsername(String username);
}
