package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.Boards;
import com.jobs.luckystage.dto.BoardDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.repository.BoardCommentRepository;
import com.jobs.luckystage.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardCommentRepository boardCommentRepository;

    @Override
    public void registerBoard(BoardDTO boardDTO) {
        Boards board=dtoToEntity(boardDTO);
        boardRepository.save(board);
    }

    @Override
    public BoardDTO readBoard(Long id) {
        Boards board = boardRepository.findById(id).get();
        board.updateReadcount();
        boardRepository.save(board);
        BoardDTO boardDTO=entityToDto(board);
        return boardDTO;
    }

    @Override
    public void updateBoard(BoardDTO boardDTO) {
        Boards board1=boardRepository.findById(boardDTO.getBoardNum()).get();
        board1.change(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board1);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        Pageable pageable=pageRequestDTO.getPageable("boardNum");

        Page<Boards> result=boardRepository
                .searchTitle(pageRequestDTO.getKeyword(),pageable);

        List<BoardDTO> dtoList=result.getContent().stream()
                .map(boards -> entityToDto(boards))
                .collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}