package com.jobs.luckystage.repository.search;

import com.jobs.luckystage.domain.Boards;
import com.jobs.luckystage.domain.QBoards;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Boards.class);
    }

    @Override
    public Page<Boards> search1(Pageable pageable) {
        QBoards board = QBoards.boards;
        JPQLQuery<Boards> query = from(board);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(board.title.contains("11"));
        booleanBuilder.or(board.content.contains("11"));
        query.where(booleanBuilder);
        query.where(board.boardNum.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);
        List<Boards> list=query.fetch();
        long count=query.fetchCount();

        return new PageImpl<Boards>(list, pageable, count);
    }

    @Override
    public Page<Boards> searchAll(String[] types, String Keyword, Pageable pageable) {
        return null;
    }
}
