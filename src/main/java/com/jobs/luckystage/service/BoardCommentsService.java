package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.BoardComments;
import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.dto.BoardCommentsDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;


public interface BoardCommentsService {
    Long register(BoardCommentsDTO boardCommentsDTO);
    BoardCommentsDTO read(Long boardCommentNum);
    void modify(BoardCommentsDTO boardCommentsDTO);
    void remove(Long boardCommentNum);
    PageResponseDTO<BoardCommentsDTO> getListOfBoard(Long boardNum, PageRequestDTO pageRequestDTO);

    default BoardComments dtoToEntity(BoardCommentsDTO boardCommentsDTO) {
        BoardComments boardComments = BoardComments.builder()
                .boardCommentNum(boardCommentsDTO.getBoardCommentNum())
                .title(boardCommentsDTO.getTitle())
                .content(boardCommentsDTO.getContent())
                .build();
        return boardComments;
    }
    default BoardCommentsDTO entityToDto(BoardComments boardComments) {
        BoardCommentsDTO boardCommentsDTO=BoardCommentsDTO.builder()
                .boardCommentNum(boardComments.getBoardCommentNum())
                .title(boardComments.getTitle())
                .content(boardComments.getContent())
                .regDate(boardComments.getRegDate())
                .build();
        return boardCommentsDTO;
    }
}