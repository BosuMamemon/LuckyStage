package com.jobs.luckystage.repository.search;

import com.jobs.luckystage.domain.Notices;
import com.jobs.luckystage.domain.QNotices;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class NoticesSearchImpl extends QuerydslRepositorySupport implements NoticesSearch {
    public NoticesSearchImpl() {
        super(Notices.class);
    }

    @Override
    public Page<Notices> searchAll(String[] types, String keyword, Pageable pageable) {
        QNotices notices=QNotices.notices;
        JPQLQuery<Notices> query = from(notices);

        if((types!=null) && (types.length>0) && keyword!=null) {
            BooleanBuilder builder = new BooleanBuilder();
            for(String type:types){
                switch(type){
                    case "t":
                        builder.or(notices.title.contains(keyword));
                        break;
                    case "c":
                        builder.or(notices.content.contains(keyword));
                        break;
//                    case "w":
//                        builder.or(notices.members.contains(keyword));
                }
            }
            query.where(builder);
        }
        query.where(notices.noticeNum.gt(0l));
        this.getQuerydsl().applyPagination(pageable, query);
        List<Notices> list= query.fetch();
        long count = query.fetchCount();
        return new PageImpl<Notices>(list, pageable, count);
    }
}
