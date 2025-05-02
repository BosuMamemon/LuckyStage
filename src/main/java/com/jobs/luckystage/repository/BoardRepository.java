package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Boards;
import com.jobs.luckystage.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Boards, Long>, BoardSearch {
    @EntityGraph(attributePaths = {"boardImages"})
    @Query("select b from Boards b where b.title like concat('%',:keyword,'%')")
    Page<Boards> searchTitle(String keyword, Pageable pageable);
}
