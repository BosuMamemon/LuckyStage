package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.FAQs;
import com.jobs.luckystage.repository.search.FAQsSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQsRepository extends JpaRepository<FAQs, Long>, FAQsSearch {
}
