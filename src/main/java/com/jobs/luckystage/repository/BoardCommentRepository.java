package com.jobs.luckystage.repository;

import com.jobs.luckystage.domain.BoardComments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardCommentRepository extends JpaRepository<BoardComments, Long> {
    @Query("select r from BoardComments r where r.boards.boardNum=:boardNum")
    Page<BoardComments> listOfBoard(Long boardNum, Pageable pageable);

    void deleteByBoards_BoardNum(long boardsNum);
}
