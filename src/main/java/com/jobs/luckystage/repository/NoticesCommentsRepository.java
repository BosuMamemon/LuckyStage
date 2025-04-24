package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Notices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticesCommentsRepository extends JpaRepository<Notices, Long> {
//    @EntityGraph(attributePaths={"imageSet"})
//    @Query("select r from NoticesComments r where r.noticesEntity.members_username=:members_username")
//    Optional<Notices> findByIdWithImages(Long members_username);


}
