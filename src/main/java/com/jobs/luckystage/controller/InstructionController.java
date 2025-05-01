package com.jobs.luckystage.controller;

import com.jobs.luckystage.dto.NoticesDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.service.NoticesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/instructions")
@RequiredArgsConstructor
public class InstructionController {
    private final NoticesService noticesService;

    @GetMapping("/instruction")
    public void instructionPage(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<NoticesDTO> responseDTOList = noticesService.list(pageRequestDTO);
        List<NoticesDTO> dtoList = responseDTOList.getDtoList();
        model.addAttribute("dtoList", dtoList);
    }
}
