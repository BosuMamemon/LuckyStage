package com.jobs.luckystage.controller;

import com.jobs.luckystage.dto.BoardCommentsDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.service.BoardCommentsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
public class BoardCommentsController {
    @Autowired
    private BoardCommentsService boardCommentsService;

    @GetMapping("/home")
    public String home() {
        return "Hello World";
    }

    @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Long> register(@RequestBody BoardCommentsDTO boardCommentsDTO) {
        log.info(boardCommentsDTO);
        Map<String,Long> map = new HashMap<>();
        Long boardCommentsNum=boardCommentsService.register(boardCommentsDTO);
        map.put("boardCommentsNum",boardCommentsNum);
        return map;
    }

    @GetMapping("/{boardCommentsNum}")
    public BoardCommentsDTO read(@PathVariable("boardCommentsNum") Long boardCommentsNum) {
        BoardCommentsDTO boardCommentsDTO = boardCommentsService.read(boardCommentsNum);
        return boardCommentsDTO;
    }
    @GetMapping("/list/{boardNum}")
    public PageResponseDTO<BoardCommentsDTO> getReplies(
            @PathVariable("boardNum") Long boardNum, PageRequestDTO pageRequest) {
        PageResponseDTO<BoardCommentsDTO> responseDTO=
                boardCommentsService.getListOfBoard(boardNum,pageRequest);
        return responseDTO;
    }
    @DeleteMapping("/{boardCommentsNum}")
    public Map<String,Long> remove(@PathVariable("boardCommentsNum") Long boardCommentsNum) {
        boardCommentsService.remove(boardCommentsNum);
        Map<String,Long> map = new HashMap<>();
        map.put("boardCommentsNum",boardCommentsNum);
        return map;
    }
    @PutMapping(value = "/{boardCommentsNum}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> modify(
            @PathVariable("boardCommentsNum") Long boardCommentsNum, @RequestBody BoardCommentsDTO boardCommentsDTO) {
        boardCommentsDTO.setBoardCommentNum(boardCommentsNum);
        boardCommentsService.modify(boardCommentsDTO);
        Map<String,Long> map = new HashMap<>();
        map.put("boardCommentsNum",boardCommentsNum);
        return map;
    }
}
