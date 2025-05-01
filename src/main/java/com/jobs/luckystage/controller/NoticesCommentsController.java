package com.jobs.luckystage.controller;


import com.jobs.luckystage.config.auth.PrincipalDetails;
import com.jobs.luckystage.dto.NoticesComentsDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.service.NoticesCommentsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/noticesComments")
@Log4j2
public class NoticesCommentsController {
    @Autowired
    private NoticesCommentsService noticesCommentsService;


    @PostMapping(value="/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register (@RequestBody NoticesComentsDTO noticesComentsDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info(noticesComentsDTO);
        Map<String, Long> map = new HashMap<>();
        Long noticeCommentNum = noticesCommentsService.register(noticesComentsDTO, principalDetails.getMember());
        map.put("noticeCommentNum", noticeCommentNum);
        return map;
    }


    @GetMapping("/{noticeCommentNum}")
    public NoticesComentsDTO read(@PathVariable("noticeCommentNum") Long noticeCommentNum){
        log.info("read"+noticeCommentNum);
        NoticesComentsDTO noticesComentsDTO = noticesCommentsService.read(noticeCommentNum);
        return noticesComentsDTO;
    }
    @GetMapping("/list/{noticeNum}")
    public PageResponseDTO<NoticesComentsDTO> getReplies(
            @PathVariable("noticeNum") Long noticeNum, PageRequestDTO pageRequest){
      log.info("noticesCommentsController"+pageRequest);
      PageResponseDTO<NoticesComentsDTO> responseDTO=noticesCommentsService.getListOfNotices(noticeNum, pageRequest);
      return responseDTO;
    }
    @DeleteMapping("/{noticeCommentNum}")
    public Map<String, Long> remove(@PathVariable("noticeCommentNum") Long noticeCommentNum){
        noticesCommentsService.remove(noticeCommentNum);
        Map<String, Long> map = new HashMap<>();
        map.put("noticeCommentNum", noticeCommentNum);
        return map;
    }
    @PutMapping("/{noticeCommentNum}")
    public Map<String, Long> modify(
            @PathVariable("noticeCommentNum") Long noticeCommentNum, @RequestBody NoticesComentsDTO noticesComentsDTO){
        noticesComentsDTO.setNoticeCommentNum(noticeCommentNum);
        noticesCommentsService.modify(noticesComentsDTO);
        Map<String, Long> map = new HashMap<>();
        map.put("noticeCommentNum", noticeCommentNum);
        return map;
    }

}
