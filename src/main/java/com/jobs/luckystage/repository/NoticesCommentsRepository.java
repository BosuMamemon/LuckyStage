package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.NoticeComments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface NoticesCommentsRepository extends JpaRepository<NoticeComments, Long> {
    @Query("select r from NoticeComments r where r.notices.noticeNum = :noticeNum")
    Page<NoticeComments>listOfNotices(Long noticeNum, Pageable pageable);

//    void deleteByNoticeComments_NoticeCommentNum(long NoticeNum);


}
