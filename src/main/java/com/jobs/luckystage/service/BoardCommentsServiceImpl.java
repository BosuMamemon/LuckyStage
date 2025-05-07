package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.BoardComments;
import com.jobs.luckystage.domain.Boards;
import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.dto.BoardCommentsDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.repository.BoardCommentRepository;
import com.jobs.luckystage.repository.BoardRepository;
import com.jobs.luckystage.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardCommentsServiceImpl implements BoardCommentsService {
    private final BoardCommentRepository boardCommentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long register(BoardCommentsDTO boardCommentsDTO) {
        BoardComments boardComments = dtoToEntity(boardCommentsDTO);
        Boards boards = boardRepository
                .findById(boardCommentsDTO.getBoardNum()).orElse(null);
        boardComments.setBoards(boards);
        boardComments.setMembers(memberRepository.findByUsername(boardCommentsDTO.getMembers()));
        Long boardCommentNum = boardCommentRepository.save(boardComments).getBoardCommentNum();
        return boardCommentNum;
    }
    @Override
    public BoardCommentsDTO read(Long boardCommentNum) {
        BoardComments boardComments=boardCommentRepository.findById(boardCommentNum).get();
        BoardCommentsDTO boardCommentsDTO=entityToDto(boardComments);
        return boardCommentsDTO;
    }

    @Override
    public void modify(BoardCommentsDTO boardCommentsDTO) {
        BoardComments boardComments = boardCommentRepository.findById(boardCommentsDTO.getBoardCommentNum()).get();
        boardComments.setContent(boardCommentsDTO.getContent());
        boardCommentRepository.save(boardComments);
    }

    @Override
    public void remove(Long boardCommentNum) {
        boardCommentRepository.deleteById(boardCommentNum);
    }

    @Override
    public PageResponseDTO<BoardCommentsDTO> getListOfBoard(Long boardNum, PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("boardCommentNum");
        Page<BoardComments> result=boardCommentRepository.listOfBoard(boardNum, pageable);

        List<BoardCommentsDTO> dtoList=result.getContent().stream()
                .map(boardComments -> entityToDto(boardComments))
                .collect(Collectors.toList());

        log.info("service / dtoList: " + dtoList);

        PageResponseDTO<BoardCommentsDTO> pageResponseDTO = PageResponseDTO.<BoardCommentsDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
        log.info("service / pageResponseDTO: " + pageResponseDTO);
        return pageResponseDTO;
    }
}
