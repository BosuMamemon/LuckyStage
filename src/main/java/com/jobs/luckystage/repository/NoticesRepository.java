package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Notices;
import com.jobs.luckystage.repository.search.NoticesSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NoticesRepository extends JpaRepository<Notices ,Long>, NoticesSearch {

    @EntityGraph(attributePaths = {"imagesSet"})
    @Query("select b from Notices b where b.noticeNum =:noticeNum")
    Optional<Notices> findByIdWithImages(Long noticeNum);

}
