package com.jobs.luckystage.repository.search;

import com.jobs.luckystage.domain.Reviews;

import java.util.List;

public interface ReviewSearch {
    List<Reviews> searchAll(String searchWord);
}
