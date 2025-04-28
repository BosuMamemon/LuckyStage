package com.jobs.luckystage.repository.search;

import com.jobs.luckystage.domain.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FAQsSearchImpl extends QuerydslRepositorySupport implements FAQsSearch {

    public FAQsSearchImpl() {
        super(FAQs.class);
    }



    @Override
    public Page<FAQs> searchAll(String[] types, String keyword, Pageable pageable) {
        QFAQs faQs =QFAQs.fAQs;
        JPQLQuery<FAQs> query = from(faQs);

        if((types!=null) && (types.length>0) && keyword!=null) {
            BooleanBuilder builder = new BooleanBuilder();
            for(String type:types){
                switch(type){
                    case "t":
                        builder.or(faQs.title.contains(keyword));
                        break;
                    case "c":
                        builder.or(faQs.content.contains(keyword));
                        break;
//                    case "w":
//                        builder.or(notices.members.contains(keyword));
                }
            }
            query.where(builder);
        }
        query.where(faQs.faqNum.gt(0l));
        this.getQuerydsl().applyPagination(pageable, query);
        List<FAQs> list= query.fetch();
        long count = query.fetchCount();
        return new PageImpl<FAQs>(list, pageable, count);

    }
}
