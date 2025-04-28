package com.jobs.luckystage.controller;

import com.jobs.luckystage.dto.FAQsDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.service.FAQsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/faqs")
public class FAQsController {
    @Autowired
    private FAQsService faqsService;
    @GetMapping("/list")
    public void list (PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<FAQsDTO> responseDTO=faqsService.list(pageRequestDTO);
        model.addAttribute("responseDTO",responseDTO);
        model.addAttribute("pageRequestDTO",pageRequestDTO);
    }
    @GetMapping("/register")
   public void registerFAQs(){
   }
   @PostMapping("/register")
   public String registerFAQs(FAQsDTO faqsDTO){
       faqsService.registerFAQs(faqsDTO);
       return "redirect:/faqs/list";
   }
   @GetMapping("/read")
   public void readFAQs(Long faqNum, PageRequestDTO pageRequestDTO, Model model) {
        log.info("1111111111111111111111111111111111111"+faqNum);

        FAQsDTO faqsDTO = faqsService.readFAQs(faqNum);
        model.addAttribute("faqs",faqsDTO);
   }
}
