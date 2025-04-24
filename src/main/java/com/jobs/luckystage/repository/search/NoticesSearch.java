package com.jobs.luckystage.repository.search;

import com.jobs.luckystage.domain.Notices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticesSearch {
    Page<Notices> searchAll(String[] types, String keyword, Pageable pageable);
}
