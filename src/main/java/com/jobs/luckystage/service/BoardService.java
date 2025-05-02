package com.jobs.luckystage.service;


import com.jobs.luckystage.domain.Boards;
import com.jobs.luckystage.dto.BoardDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import java.time.ZoneId;

public interface BoardService {
    void registerBoard(BoardDTO boardDTO);
    BoardDTO readBoard(Long id);
    void updateBoard(BoardDTO boardDTO);
    void deleteBoard(Long id);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    default Boards dtoToEntity(BoardDTO boardDTO) {
        Boards board=Boards.builder()
//                .boardNum(boardDTO.getBoardNum())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .build();
        return board;
    }
    default BoardDTO entityToDto(Boards board) {
        BoardDTO boardDTO=BoardDTO.builder()
                .boardNum(board.getBoardNum())
                .title(board.getTitle())
                .content(board.getContent())
                .readcount(board.getReadcount())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .members(board.getMembers().getUsername())
                .nickname(board.getMembers().getNickname())
                .build();
        return boardDTO;
    }
}
