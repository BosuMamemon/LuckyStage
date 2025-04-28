package com.jobs.luckystage.repository.search;

import com.jobs.luckystage.domain.Concerts;

import java.util.List;

public interface ConcertSearch {
    List<Concerts> searchAll(String type, String searchWord);
}
