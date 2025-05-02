package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.domain.NoticeComments;
import com.jobs.luckystage.dto.NoticesCommentsDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;

public interface NoticesCommentsService {
    Long register(NoticesCommentsDTO noticesCommentsDTO, Members member);
    NoticesCommentsDTO read(Long noticeCommentNum);
    void modify(NoticesCommentsDTO noticesCommentsDTO);
    void remove(Long noticeCommentNum);
    PageResponseDTO<NoticesCommentsDTO> getListOfNotices(Long noticeNum, PageRequestDTO pageRequestDTO);

    default NoticeComments dtoToEntity(NoticesCommentsDTO noticesCommentsDTO) {
        NoticeComments noticeComments = NoticeComments.builder()
                .content(noticesCommentsDTO.getContent())
                .build();

        return noticeComments;
    }
    default NoticesCommentsDTO entityToDto(NoticeComments noticeComments) {
        NoticesCommentsDTO noticesCommentsDTO = NoticesCommentsDTO.builder()
                .noticeCommentNum(noticeComments.getNoticeCommentNum())
                .content(noticeComments.getContent())
                .regDate(noticeComments.getRegDate())
                .members_username(noticeComments.getMembers().getUsername())
                .nickname(noticeComments.getMembers().getNickname())
                .noticeNum(noticeComments.getNotices().getNoticeNum())
                .build();
        return noticesCommentsDTO;
    }
}
