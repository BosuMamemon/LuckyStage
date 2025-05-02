package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.domain.NoticeComments;
import com.jobs.luckystage.dto.NoticesComentsDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;

public interface NoticesCommentsService {
    Long register(NoticesComentsDTO noticesComentsDTO, Members member);
    NoticesComentsDTO read(Long noticeCommentNum);
    void modify(NoticesComentsDTO noticesComentsDTO);
    void remove(Long noticeCommentNum);
    PageResponseDTO<NoticesComentsDTO> getListOfNotices(Long noticeNum, PageRequestDTO pageRequestDTO);

    default NoticeComments dtoToEntity(NoticesComentsDTO noticesComentsDTO) {
        NoticeComments noticeComments = NoticeComments.builder()
             //   .noticeCommentNum(noticesComentsDTO.getNoticeNum())
                .content(noticesComentsDTO.getContent())
                .title("title")
                .build();

        return noticeComments;
    }
    default NoticesComentsDTO entityToDto(NoticeComments noticeComments) {
        NoticesComentsDTO noticesComentsDTO = NoticesComentsDTO.builder()
                .noticeCommentNum(noticeComments.getNoticeCommentNum())
                .content(noticeComments.getContent())
//                .regDate(noticeComments.getRegDate())
                .noticeNum(noticeComments.getNotices().getNoticeNum())

                .build();
        return noticesComentsDTO;
    }
}
