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
    public List<Reviews> searchAll(String searchWord) {
        QReviews qReviews = QReviews.reviews;
        JPQLQuery<Reviews> query = from(qReviews);
        List<Reviews> reviewList;

        if(searchWord != null) {
            BooleanBuilder builder = new BooleanBuilder();
            builder.or(qReviews.title.contains(searchWord));
            builder.or(qReviews.content.contains(searchWord));
            query.where(builder);
        }

        reviewList = query.orderBy(qReviews.reviewNum.desc()).fetch();
        return reviewList;
    }
}
