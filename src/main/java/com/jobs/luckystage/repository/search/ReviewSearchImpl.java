package com.jobs.luckystage.repository.search;

import com.jobs.luckystage.domain.QReviews;
import com.jobs.luckystage.domain.Reviews;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ReviewSearchImpl extends QuerydslRepositorySupport implements ReviewSearch {
    public ReviewSearchImpl() {
        super(Reviews.class);
    }

    @Override
    public List<Reviews> searchAll(String type, String searchWord) {
        QReviews qReviews = QReviews.reviews;
        JPQLQuery<Reviews> query = from(qReviews);
        List<Reviews> reviewList;
        BooleanBuilder builder = new BooleanBuilder();

        if(searchWord != null) {
            builder.or(qReviews.title.contains(searchWord));
            builder.or(qReviews.content.contains(searchWord));
        }

        if(type != null) {
            builder.and(qReviews.members.username.eq(type));
        }

        query.where(builder);

        reviewList = query.orderBy(qReviews.reviewNum.desc()).fetch();
        return reviewList;
    }
}
