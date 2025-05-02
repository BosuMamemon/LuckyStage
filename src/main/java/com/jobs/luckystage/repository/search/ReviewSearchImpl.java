package com.jobs.luckystage.repository.search;

import com.jobs.luckystage.domain.QReviews;
import com.jobs.luckystage.domain.Reviews;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ReviewSearchImpl extends QuerydslRepositorySupport implements ReviewSearch {
    public ReviewSearchImpl() {
        super(Reviews.class);
    }

    @Override
    public Page<Reviews> searchAll(String type, String searchWord, Pageable pageable) {
        QReviews qReviews = QReviews.reviews;
        JPQLQuery<Reviews> query = from(qReviews);
        List<Reviews> reviewList;
        BooleanBuilder builder = new BooleanBuilder();

        if(searchWord != null) {
            builder.or(qReviews.title.contains(searchWord));
            builder.or(qReviews.content.contains(searchWord));
            builder.or(qReviews.concerts.title.contains(searchWord));
        }

        query.where(builder);
        this.getQuerydsl().applyPagination(pageable, query);
        reviewList = query.orderBy(qReviews.reviewNum.desc()).fetch();
        long count = query.fetchCount();
        return new PageImpl<>(reviewList, pageable, count);
    }

    @Override
    public Page<Reviews> searchAllByUsername(String username, String type, String searchWord, Pageable pageable) {
        QReviews qReviews = QReviews.reviews;
        JPQLQuery<Reviews> query = from(qReviews);
        List<Reviews> reviewList;
        BooleanBuilder builder = new BooleanBuilder();


        if(searchWord != null) {
            builder.or(qReviews.title.contains(searchWord));
            builder.or(qReviews.content.contains(searchWord));
            builder.or(qReviews.concerts.title.contains(searchWord));
        }

        builder.and(qReviews.members.username.eq(username));

        query.where(builder);
        this.getQuerydsl().applyPagination(pageable, query);
        reviewList = query.orderBy(qReviews.reviewNum.desc()).fetch();
        long count = query.fetchCount();
        return new PageImpl<>(reviewList, pageable, count);
    }
}
