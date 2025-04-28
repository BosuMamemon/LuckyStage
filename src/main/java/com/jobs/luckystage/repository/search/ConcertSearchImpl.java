package com.jobs.luckystage.repository.search;

import com.jobs.luckystage.domain.Concerts;
import com.jobs.luckystage.domain.QConcerts;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ConcertSearchImpl extends QuerydslRepositorySupport implements ConcertSearch {
    public ConcertSearchImpl() {
        super(Concerts.class);
    }

    @Override
    public List<Concerts> searchAll(String type, String searchWord) {
        QConcerts qConcerts = QConcerts.concerts;
        JPQLQuery<Concerts> query = from(qConcerts);
        List<Concerts> concertsList;

        if(searchWord != null) {
            BooleanBuilder builder = new BooleanBuilder();
            builder.or(qConcerts.title.contains(searchWord));
            query.where(builder);
        }


        if(type != null) {
            switch(type) {
                case "startDate": query.orderBy(qConcerts.startDate.asc()); break;
                case "rating": query.orderBy(qConcerts.rating.desc()); break;
                case "hitcount": query.orderBy(qConcerts.hitcount.desc()); break;
            }
        }

        concertsList = query.fetch();
        return concertsList;
    }
}
