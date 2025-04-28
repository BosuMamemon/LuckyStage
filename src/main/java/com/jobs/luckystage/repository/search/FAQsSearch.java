package com.jobs.luckystage.repository.search;


import com.jobs.luckystage.domain.Boards;
import com.jobs.luckystage.domain.FAQs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FAQsSearch {
    Page<FAQs> searchAll(String[] types, String keyword, Pageable pageable);
}
