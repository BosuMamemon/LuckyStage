package com.jobs.luckystage.repository.search;


import com.jobs.luckystage.domain.Boards;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Boards> search1(Pageable pageable);
    Page<Boards> searchAll(String[] types, String Keyword,Pageable pageable);
}
