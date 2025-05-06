package com.jobs.luckystage.controller;

import com.jobs.luckystage.dto.BoardDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
    }
    @GetMapping("/register")
    public void register(){
    }
    @PostMapping("/register")
    public String register(BoardDTO boardDTO) {
        boardService.registerBoard(boardDTO);
        return "redirect:/board/list";
    }
    @GetMapping({"/read","/modify"})
    public void read_modify(Long boardNum, PageRequestDTO pageRequestDTO, Model model) {
        BoardDTO boardDTO = boardService.readBoard(boardNum);
        model.addAttribute("board", boardDTO);
    }
    @GetMapping("/remove")
    public String remove(Long boardNum) {
        boardService.deleteBoard(boardNum);
        return "redirect:/board/list";
    }
    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO, PageRequestDTO pageRequestDTO) {
        boardService.updateBoard(boardDTO);
        return "redirect:/board/read?boardNum="+boardDTO.getBoardNum();
    }
}
