package com.jobs.luckystage.service;


import com.jobs.luckystage.domain.Boards;
import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.dto.BoardDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public interface BoardService {
    void registerBoard(BoardDTO boardDTO);
    BoardDTO readBoard(Long id);
    void updateBoard(BoardDTO boardDTO);
    void deleteBoard(Long id);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    default Boards dtoToEntity(BoardDTO boardDTO) {
        Boards board=Boards.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .build();

        if(boardDTO.getFilename() != null) {
            boardDTO.getFilename().forEach(fileName -> {
                String[] arr = fileName.split("_");
                board.addImage(arr[0], arr[1]);
            });
        }
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
        List<String> filename=board.getBoardImages().stream()
                .sorted()
                .map(img ->img.getUuid()+"_"+img.getFilename())
                .collect(Collectors.toList());
        boardDTO.setFilename(filename);
        return boardDTO;
    }
}
