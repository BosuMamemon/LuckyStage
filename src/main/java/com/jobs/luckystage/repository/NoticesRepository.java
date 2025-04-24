package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Notices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NoticesRepository extends JpaRepository<Notices ,Long> {
    //@EntityGraph(attributePaths = {"imageSet"})
//    @Query("select b from Notices b where b.members_username =:members_username")
    //Optional<Notices> findByIdWithImages(Long notices_notice_num);

}
