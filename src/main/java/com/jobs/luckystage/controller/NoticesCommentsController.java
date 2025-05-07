package com.jobs.luckystage.controller;


import com.jobs.luckystage.config.auth.PrincipalDetails;
import com.jobs.luckystage.dto.NoticesCommentsDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.service.NoticesCommentsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public Map<String, Long> register (@RequestBody NoticesCommentsDTO noticesCommentsDTO,
                                       @AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("controller / request / noticesCommentsDTO: "+ noticesCommentsDTO.toString());
        Map<String, Long> map = new HashMap<>();
        Long noticeCommentNum = noticesCommentsService.register(noticesCommentsDTO, principalDetails.getMember());
        map.put("noticeCommentNum", noticeCommentNum);
        log.info("controller / response / map: " + map);
        return map;
    }


    @GetMapping("/{noticeCommentNum}")
    public NoticesCommentsDTO read(@PathVariable("noticeCommentNum") Long noticeCommentNum){
        NoticesCommentsDTO noticesCommentsDTO = noticesCommentsService.read(noticeCommentNum);
        log.info("noticesCommentsDTO: " + noticesCommentsDTO);
        return noticesCommentsDTO;
    }

    @GetMapping("/list/{noticeNum}")
    public PageResponseDTO<NoticesCommentsDTO> getReplies(
            @PathVariable("noticeNum") Long noticeNum, PageRequestDTO pageRequest){
        log.info("noticeNum:" + noticeNum);
        PageResponseDTO<NoticesCommentsDTO> responseDTO = noticesCommentsService.getListOfNotices(noticeNum, pageRequest);
        log.info("responseDTO:" + responseDTO.getDtoList());
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
            @PathVariable("noticeCommentNum") Long noticeCommentNum, @RequestBody NoticesCommentsDTO noticesCommentsDTO) {
        noticesCommentsDTO.setNoticeCommentNum(noticeCommentNum);
        noticesCommentsService.modify(noticesCommentsDTO);
        Map<String, Long> map = new HashMap<>();
        map.put("noticeCommentNum", noticeCommentNum);
        return map;
    }

}
