package com.jobs.luckystage.repository.search;

import com.jobs.luckystage.domain.Reviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewSearch {
    Page<Reviews> searchAll(String type, String searchWord, Pageable pageable);
}
