package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.Notices;
import com.jobs.luckystage.repository.search.NoticesSearch;
import org.hibernate.annotations.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.authentication.jaas.JaasAuthenticationCallbackHandler;

import java.util.Optional;

public interface NoticesCommentsRepository extends JpaRepository<Notices, Long> , NoticesSearch {
//    @EntityGraph(attributePaths={"imageSet"})
//    @Query("select r from NoticesComments r where r.noticesEntity.members_username=:members_username")
//    Optional<Notices> findByIdWithImages(Long members_username);


}
